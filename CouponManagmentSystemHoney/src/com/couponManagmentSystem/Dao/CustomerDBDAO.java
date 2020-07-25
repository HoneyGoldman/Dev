package com.couponManagmentSystem.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import com.couponManagmentSystem.Beans.Category;
import com.couponManagmentSystem.Beans.Coupon;
import com.couponManagmentSystem.Beans.Customer;
import com.couponManagmentSystem.couponSystemExceptions.CouponSystemException;

public class CustomerDBDAO implements CustomerDAO {
	public CustomerDBDAO() {
		super();
	}
	private CouponDAO coupondao=new CouponDBDAO();

	@Override
	public boolean isCustomerExists(String email, String password) throws CouponSystemException {

		String sqlIsExists = "select * from customers where email= ? and password=?";

		Connection con = ConnectionPool.getInstance().getConnection();
		try {
			java.sql.PreparedStatement stmt = con.prepareStatement(sqlIsExists);
			stmt.setString(1, email);
			stmt.setString(2, password);

			ResultSet rs = stmt.executeQuery();
			ConnectionPool.getInstance().restoreConnection(con);
			if (rs != null) {
				return true;
			} else

				return false;

		} catch (SQLException e) {

			ConnectionPool.getInstance().restoreConnection(con);
			throw new CouponSystemException("Company Not Found", e);
		} finally {
			ConnectionPool.getInstance().restoreConnection(con);

		}
	}

	@Override
	public void addCustomer(Customer customer) throws CouponSystemException {
		Connection con = ConnectionPool.getInstance().getConnection();
		String sqlAddCustomer = "insert into customers (first_name,last_name,email,password) values (?,?,?,?)";

		try {
			PreparedStatement pstmt = con.prepareStatement(sqlAddCustomer);

			pstmt.setString(1, customer.getFirstName());
			pstmt.setString(2, customer.getLastName());
			pstmt.setString(3, customer.getEmail());
			pstmt.setString(4, customer.getPassword());
			if (customer.getCoupons() != null) {
				// add customer coupons
				List<Coupon> customerCoupons = customer.getCoupons();
				while (customerCoupons.iterator().hasNext()) {
					CustomerDBDAO dao = new CustomerDBDAO();
					dao.addCustomerCoupon(customerCoupons.indexOf(customerCoupons), customer.getId());
					ConnectionPool.getInstance().restoreConnection(con);
				}
			}
			System.out.println("From add customer customer DBDAO "+pstmt);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			ConnectionPool.getInstance().restoreConnection(con);
			throw new CouponSystemException("Failed To add Customer", e);
		} finally {
			ConnectionPool.getInstance().restoreConnection(con);

		}

	}

	@Override
	public void updateCustomer(Customer customer) throws CouponSystemException {
		// TODO Auto-generated method stub
		if (!isCustomerExists(customer.getFirstName(), customer.getPassword())) {
			throw new CouponSystemException("the customer doesn't exists");
		} else {
			System.out.println("from update in customer DBDAO");
			String sqlCommand = "update customers set first_name='" + customer.getFirstName() + "' , last_name='"
					+ customer.getLastName() + "' , email='" + customer.getEmail() + "' , password='"
					+ customer.getPassword() + "' where id='" + customer.getId() + "'";
			sqlVoidExecuter(sqlCommand);
			System.out.println(sqlCommand);

		}
	}

	@Override
	public void deleteCustomer(int customerID) throws CouponSystemException {
		sqlVoidExecuter("delete from customers where id='" + customerID + "'");
	}

	@Override
	public ArrayList<Customer> getAllCustomers() throws CouponSystemException {
		// TODO Auto-generated method stub
		ArrayList<Customer> allCustomers = new ArrayList<Customer>();
		String sqlGetAllCustomers = "select * from customers";
		Connection con = ConnectionPool.getInstance().getConnection();

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sqlGetAllCustomers);

			while (rs.next()) {
				Customer customer = new Customer();
				customer.setId(rs.getInt("id"));
				customer.setFirstName(rs.getString("first_name"));
				customer.setLastName(rs.getString("last_name"));
				customer.setEmail(rs.getString("email"));
				customer.setPassword(rs.getString("password"));
				allCustomers.add(customer);
			}
			ConnectionPool.getInstance().restoreConnection(con);
		} catch (SQLException e) {

			ConnectionPool.getInstance().restoreConnection(con);
			throw new CouponSystemException("Failed to Get All Customers", e);
		} finally {
			ConnectionPool.getInstance().restoreConnection(con);

		}

		return allCustomers;

	}

	@Override
	public Customer getCustomer(int customerID) throws CouponSystemException {
		return sqlExecuterForSelect("select * from customers where id='" + customerID+"'");
	}
	@Override
	public Customer getOneCustomer(String name) throws CouponSystemException {
		return sqlExecuter("select * from customers where cust_name= '" + name + "'");
	}
	@Override
	public ArrayList<Coupon> getAllCustomerCoupons(int customerId) throws CouponSystemException {
		ArrayList<Coupon> allCustomerCoupons = new ArrayList<Coupon>();
		String sqlGetAllCustomerCoupons = "select * from customers_vs_coupons where customer_id='" + customerId + "'";

		Connection con = ConnectionPool.getInstance().getConnection();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sqlGetAllCustomerCoupons);

			while (rs.next()) {
				Coupon temp = new CouponDBDAO().getCoupon(rs.getInt("coupon_id"));
				allCustomerCoupons.add(temp);
			}
			ConnectionPool.getInstance().restoreConnection(con);
		} catch (SQLException e) {

			ConnectionPool.getInstance().restoreConnection(con);
			e.printStackTrace();
			throw new CouponSystemException("Failed to get all Customer Coupons", e);
		} finally {
			ConnectionPool.getInstance().restoreConnection(con);

		}
		return allCustomerCoupons;
	}
	@Override
	public ArrayList<Coupon> getCoupons() throws CouponSystemException {

		ArrayList<Coupon> allCoupons = new ArrayList<Coupon>();
		String sqlGetAllCoupons = "select * from coupons";

		Connection con = ConnectionPool.getInstance().getConnection();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sqlGetAllCoupons);

			while (rs.next()) {
				Coupon coupon = new Coupon();
				coupon.setId(rs.getInt("id"));
				coupon.setCategory(rs.getInt("type"));
				coupon.setTitle(rs.getString("title"));
				coupon.setDescription(rs.getString("message"));
				coupon.setStartDate((rs.getDate("start_Date")));
				coupon.setEndDate((rs.getDate("end_Date")));
				coupon.setAmount(rs.getInt("amount"));
				coupon.setPrice(rs.getDouble("price"));
				coupon.setImage(rs.getString("image"));

				allCoupons.add(coupon);
			}
			ConnectionPool.getInstance().restoreConnection(con);

		} catch (SQLException e) {

			ConnectionPool.getInstance().restoreConnection(con);
			throw new CouponSystemException("Failed to get all Coupons", e);
		} finally {
			ConnectionPool.getInstance().restoreConnection(con);

		}
		return allCoupons;
	}
	@Override
	public Customer sqlExecuter(String sql) throws CouponSystemException {
		Connection con = ConnectionPool.getInstance().getConnection();
		Customer customer = new Customer();

		try {
			System.out.println("sqlExecuter Started command -" + sql);
			Statement stmt = con.createStatement();
			stmt.executeUpdate(sql);
			ResultSet rs = null;
			if (rs.equals(null)) {
				ConnectionPool.getInstance().restoreConnection(con);
				return null;
			}
			while (rs.next()) {
				customer.setId(rs.getInt("id"));
				customer.setFirstName(rs.getString("first_name"));
				customer.setLastName(rs.getString("last_name"));
				customer.setEmail(rs.getString("email"));
				customer.setPassword(rs.getString("password"));
			}
			ConnectionPool.getInstance().restoreConnection(con);
		} catch (SQLException e) {

			throw new CouponSystemException("from sql Executer- " + sql, e);
		} finally {
			ConnectionPool.getInstance().restoreConnection(con);

		}

		return customer;

	}
	@Override
	public void addCustomerCoupon(int couponId, int customerId) throws CouponSystemException {
		Customer temp=new Customer();
		temp.setId(customerId);
		Coupon temp1=new Coupon();
		temp1.setId(couponId);
		if (coupondao.isCustomerCouponExist(temp, temp1)) {
			throw new CouponSystemException("this customer already bought this coupon! coupon id="+couponId+" customer id="+ customerId);
			}
			else
				sqlVoidExecuter("INSERT INTO customers_vs_coupons (customer_id, coupon_id) " + "VALUES ('" + customerId
						+ "','" + couponId + "');");
		
		System.out.println("from add customer coupon-"+"INSERT INTO customers_vs_coupons (customer_id, coupon_id) " + "VALUES ('" + customerId
				+ "','" + couponId + "');");
	}
	@Override
	public void sqlVoidExecuter(String sql) throws CouponSystemException {

		Connection con = ConnectionPool.getInstance().getConnection();
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			System.out.println("from sql void excuter "+sql);
			pstmt.execute(sql);
			ConnectionPool.getInstance().restoreConnection(con);
		}

		catch (SQLException e) {

			ConnectionPool.getInstance().restoreConnection(con);
			e.printStackTrace();
			throw new com.couponManagmentSystem.couponSystemExceptions.CouponSystemException("Failed to execut " + sql,
					e);
		} finally {

			ConnectionPool.getInstance().restoreConnection(con);
		}
	}
	@Override
	public void deleteCustomerFromCustomersVSCoupons(int customerId) throws CouponSystemException {
		sqlVoidExecuter(" delete from customers_vs_coupons where customer_id='" + customerId + "'");
	}
	@Override
	public Customer getCustomerByEmailPassword(String email, String password) throws CouponSystemException {
		return sqlExecuterForSelect(
				"SELECT * FROM customers where email='" + email + "' and password='" + password + "'");
	}

	// this method only delete the link between the customer and the coupons that he
	// bought (doesnt delete the coupons from DB).
	@Override
	public void deleteCustomerCoupons(Customer customer) throws CouponSystemException {
		sqlExecuter("delete from customers_vs_coupons where customer_id='" + customer.getId() + "'");
	}

	// this method only delete the link between the customer and the coupon that he
	// bought (doesn't delete the coupons from DB).
	@Override
	public void deleteCustomerCoupon(Customer customer, Coupon coupon) throws CouponSystemException {
		sqlExecuter("delete from customers_vs_coupons where customer_id='" + customer.getId() + "' and coupon_id='"
				+ coupon.getId() + "'");
	}

	// only delete the link between customers and coupon
	@Override
	public void deleteCouponForAllCustomers(int couponId) throws CouponSystemException {
		sqlVoidExecuter("delete from customers_vs_coupons where coupon_id='" + couponId + "'");
	}
	@Override
	public List<Customer> getCustomersByCouponId(int couponId) throws CouponSystemException {
		List<Customer> allCustomersByCouponId = new ArrayList<Customer>();
		String sqlGetAllCustomersByCouponId = "select * from customers_vs_coupons where coupon_id='" + couponId + "'";
		Connection con = ConnectionPool.getInstance().getConnection();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sqlGetAllCustomersByCouponId);

			while (rs.next()) {
				Customer temp = new CustomerDBDAO().getCustomer(rs.getInt("customer_id"));
				allCustomersByCouponId.add(temp);
			}
			ConnectionPool.getInstance().restoreConnection(con);
		} catch (SQLException e) {

			ConnectionPool.getInstance().restoreConnection(con);
			e.printStackTrace();
			throw new CouponSystemException("Failed to get all Customer Coupons", e);
		} finally {
			ConnectionPool.getInstance().restoreConnection(con);

		}
		return allCustomersByCouponId;
	}
	@Override
	public List<Coupon> getAllCustomerCouponsByCategory(int categoryId, int customerId)
			throws CouponSystemException {
		String sql ="SELECT coupons.id,coupons.company_id,coupons.category_id,coupons.title,coupons.description,"
			+	"coupons.start_date,coupons.end_date,coupons.amount,coupons.price,coupons.image,"
			+" id FROM coupons JOIN customers_vs_coupons"
			 +" ON  coupons.id=customers_vs_coupons.coupon_id WHERE coupons.category_id='"+categoryId+"' "
			+"and customers_vs_coupons.customer_id='"+customerId+"'";
	
		return new CouponDBDAO().CouponGenericGetter(sql);

	}
	@Override
	public List<Coupon> getAllCustomerCouponsByMaxPrice(Double maxprice, int customerId) throws CouponSystemException {
		String sql = "SELECT coupons.id,coupons.company_id,coupons.category_id,coupons.title,coupons.description," + 
				"					coupons.start_date,coupons.end_date,coupons.amount,coupons.price,coupons.image," + 
				"				id FROM coupons JOIN customers_vs_coupons" + 
				"				 ON  coupons.id=customers_vs_coupons.coupon_id WHERE coupons.price<" +maxprice+ 
				"				and customers_vs_coupons.customer_id='"+customerId+"'";
		return new CouponDBDAO().CouponGenericGetter(sql);
	}
	
	public List<Coupon> getAllCouponsByCategory(int categoryId) throws CouponSystemException {
		String sql = "SELECT * from coupons where category_id='"+categoryId+"'";
		return new CouponDBDAO().CouponGenericGetter(sql);
	}
	@Override
	public Customer sqlExecuterForSelect(String sql) throws CouponSystemException {
		Connection con = ConnectionPool.getInstance().getConnection();
		Customer customer = new Customer();

		try {
			System.out.println("sqlExecuter Started command -" + sql);
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.equals(null)) {
				ConnectionPool.getInstance().restoreConnection(con);
				return null;
			}
			while (rs.next()) {
				customer.setId(rs.getInt("id"));
				customer.setFirstName(rs.getString("first_name"));
				customer.setLastName(rs.getString("last_name"));
				customer.setEmail(rs.getString("email"));
				customer.setPassword(rs.getString("password"));
			}
			ConnectionPool.getInstance().restoreConnection(con);
		} catch (SQLException e) {

			throw new CouponSystemException("from sql Executer- " + sql, e);
		} finally {
			ConnectionPool.getInstance().restoreConnection(con);

		}

		return customer;

	}
	@Override
	public int categoryGetter(Category category) throws CouponSystemException {
		Connection con = ConnectionPool.getInstance().getConnection();
		int categoryId = 0;

		try {
			System.out.println("categoryGetter Started command -");
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM categories where name='" + category.ordinal() + "'");
			if (rs.equals(null)) {
				ConnectionPool.getInstance().restoreConnection(con);
				return 0;
			}
			while (rs.next()) {
				categoryId = rs.getInt("id");
			}
			ConnectionPool.getInstance().restoreConnection(con);
			return categoryId;
		} catch (SQLException e) {
			throw new CouponSystemException("from categoryGetter", e);
		} finally {
			ConnectionPool.getInstance().restoreConnection(con);

			return categoryId;
		}

	}


}