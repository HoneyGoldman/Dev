package com.couponManagmentSystem.Dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.couponManagmentSystem.Beans.Category;
import com.couponManagmentSystem.Beans.Company;
import com.couponManagmentSystem.Beans.Coupon;
import com.couponManagmentSystem.Beans.Customer;
import com.couponManagmentSystem.couponSystemExceptions.*;

public class CouponDBDAO implements CouponDAO {

	public CouponDBDAO() {
		super();
	}

	@Override
	public boolean isExists(int id) throws CouponSystemException {
		Connection con = ConnectionPool.getInstance().getConnection();
		System.out.println("is exist ok");
		String sqlIsExists = "select * from coupons where id=" + id;
		java.sql.Statement stmt;
		try {
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sqlIsExists);
			if (rs != null) {

				ConnectionPool.getInstance().restoreConnection(con);
				return true;
			}

		} catch (SQLException e) {
			ConnectionPool.getInstance().restoreConnection(con);
			throw new CouponSystemException("No Coupon Found",e);
		} finally {
			ConnectionPool.getInstance().restoreConnection(con);

		}
		return false;
	}

	//need to fix this
	@Override
	public void createCoupon(Coupon coupon) throws CouponSystemException {
		System.out.println("from create coupon in CouponDBDO-"+coupon);
		String sqlAddCoupon = "insert into coupons (company_id,category_id,title,description,start_date,end_date"
				+ ",amount,price,image) values (?,?,?,?,?,?,?,?,?)";
		Connection con = ConnectionPool.getInstance().getConnection();
System.out.println("from create coupon in CouponDBDAO");
		try {
			coupon.setId(1);//set id because if id=null it fails (sql exception).
			PreparedStatement pstmt = con.prepareStatement(sqlAddCoupon);
			pstmt.setInt(1, coupon.getCompanyId());
			pstmt.setInt(2, (coupon.getCategoryId()));
			pstmt.setString(3, coupon.getTitle());
			pstmt.setString(4, coupon.getDescription());
			pstmt.setDate(5, (coupon.getStartDate()));
			pstmt.setDate(6, (coupon.getEndDate()));
			pstmt.setInt(7, coupon.getAmount());
			pstmt.setDouble(8, coupon.getPrice());
			pstmt.setString(9, coupon.getImage());
			System.out.println(pstmt.toString());
			
			pstmt.executeUpdate();
			System.out.println(pstmt.toString());
			ConnectionPool.getInstance().restoreConnection(con);
		} catch (SQLException e) {

			ConnectionPool.getInstance().restoreConnection(con);
			e.printStackTrace();
			throw new CouponSystemException("Failed to Add Coupon",e);
		} finally {
			ConnectionPool.getInstance().restoreConnection(con);

		}
	}

	@Override
	public void updateCoupon(Coupon coupon) throws CouponSystemException {
		if (isExists(coupon.getId())) {
			String sqlUpdateCoupon = "update coupons set category_id=?,title=?,description=? ,start_date=?,end_Date=?,amount=?,price=?,image=? where id=?";
			Connection con = ConnectionPool.getInstance().getConnection();
			try {
				PreparedStatement pstmt = con.prepareStatement(sqlUpdateCoupon);

				pstmt.setInt(1, (coupon.getCategoryId()));
				pstmt.setString(2, coupon.getTitle());
				pstmt.setString(3, coupon.getDescription());
				pstmt.setDate(4, coupon.getStartDate());
				pstmt.setDate(5, coupon.getEndDate());
				pstmt.setInt(6, coupon.getAmount());
				pstmt.setDouble(7, coupon.getPrice());
				pstmt.setString(8, coupon.getImage());
				pstmt.setInt(9, coupon.getId());
System.out.println(pstmt.toString());
				pstmt.executeUpdate();
				ConnectionPool.getInstance().restoreConnection(con);
			} catch (SQLException e) {

				ConnectionPool.getInstance().restoreConnection(con);
				e.printStackTrace();
				throw new CouponSystemException("Failed to Updte Coupon",e);
			} finally {
				ConnectionPool.getInstance().restoreConnection(con);

			}
		}
	}

	@Override
	public void removeCoupon(int id) throws CouponSystemException {
		if (!isExists(id)) {

			throw new CouponSystemException("the coupon doesn't exsist");
		} else {
			Connection con = ConnectionPool.getInstance().getConnection();
			String sqlDeleteCoupon = "delete from coupons where id= " + id;
			try {
				Statement stmt = con.createStatement();
				stmt.executeUpdate(sqlDeleteCoupon);
				ConnectionPool.getInstance().restoreConnection(con);
			} catch (SQLException e) {

				ConnectionPool.getInstance().restoreConnection(con);
				throw new CouponSystemException("Failed to delete",e);

			} finally {

				ConnectionPool.getInstance().restoreConnection(con);
			}

		}

	}

	@Override
	public ArrayList<Coupon> getAllCoupons() throws CouponSystemException {
		ArrayList<Coupon> allCoupons = new ArrayList<Coupon>();
		String sqlGetAllCoupons = "select * from coupons";

		Connection con = ConnectionPool.getInstance().getConnection();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sqlGetAllCoupons);

			while (rs.next()) {
				Coupon coupon = new Coupon();
				coupon.setId(rs.getInt("id"));
				coupon.setCompanyId(rs.getInt("company_id"));
				coupon.setCategoryId(rs.getInt("category_id"));
				coupon.setTitle(rs.getString("title"));
				coupon.setDescription(rs.getString("description"));
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
			throw new CouponSystemException("Failed to get all Coupons",e);
		} finally {
			ConnectionPool.getInstance().restoreConnection(con);

		}
		return allCoupons;

	}

	public List<Coupon> getAllCompanyCoupons(int companyId) throws CouponSystemException {
		return CouponGenericGetter("select * from coupons where company_id='" + companyId + "'");
	}

	@Override
	public Coupon getCoupon(int id) throws CouponSystemException {
		String sqlGetCoupon = "select * from coupons where id='" + id + "'";
		System.out.println(sqlGetCoupon);
		Connection con = ConnectionPool.getInstance().getConnection();
		Coupon coupon = new Coupon();

		try {
			Statement stmt = con.createStatement();
			ResultSet rs;

			rs = stmt.executeQuery(sqlGetCoupon);

			while (rs.next()) {

				coupon.setId(rs.getInt("id"));
				coupon.setCompanyId(rs.getInt("company_id"));
				coupon.setCategoryId(rs.getInt("category_id"));
				coupon.setTitle(rs.getString("title"));
				coupon.setDescription(rs.getString("description"));
				coupon.setStartDate((rs.getDate("start_Date")));
				coupon.setEndDate((rs.getDate("end_Date")));
				coupon.setAmount(rs.getInt("amount"));
				coupon.setPrice(rs.getDouble("price"));
				coupon.setImage(rs.getString("image"));

			}
			ConnectionPool.getInstance().restoreConnection(con);
		} catch (SQLException e) {

			ConnectionPool.getInstance().restoreConnection(con);
			e.printStackTrace();
			throw new CouponSystemException("coupon not found",e);
		} finally {
			ConnectionPool.getInstance().restoreConnection(con);
		}

		return coupon;

	}
	@Override
	public List<Coupon> getCouponByType(String type) throws CouponSystemException {

		String sql = " select * from coupons where type" + type;
		return CouponGenericGetter(sql);
	}
	@Override
	public List<Coupon> getAllExpiredCoupons() throws CouponSystemException {
		java.sql.Date now=java.sql.Date.valueOf(LocalDate.now());
		String sql = "select * from coupons where END_DATE < '"+now+"'";
		System.out.println("From getAllExpiredCoupons- "+sql);
		return this.CouponGenericGetter(sql);
	}

	@Override
	public List<Coupon> getAllCompanyCouponsByMaxPrice(int companyId, Double maxPrice) throws CouponSystemException {
		String sqlGetAllCompanyCouponsByMaxPrice = "select * from coupons where company_id=" + companyId + " and Price<"
				+ maxPrice;
		return CouponGenericGetter(sqlGetAllCompanyCouponsByMaxPrice);
	}

	public List<Coupon> getAllcustomerCoupons(int customerId) throws CouponSystemException {

		Connection con = ConnectionPool.getInstance().getConnection();
		try {
			List<Coupon> coupons = new ArrayList<Coupon>();
			String sql = "select * from customers_vs_coupons where customer_id=" + customerId;
			Statement stmt = con.createStatement();
			ResultSet rs;

			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				coupons.add(getCoupon(rs.getInt("id")));
			}

			ConnectionPool.getInstance().restoreConnection(con);
			return CouponGenericGetter(sql);
		} catch (Exception e) {
			e.printStackTrace();
			throw new CouponSystemException("Failed to get all Customer Coupons",e);
		}
		finally {
			ConnectionPool.getInstance().restoreConnection(con);
		}
	}
	@Override
	public List<Coupon> CouponGenericGetter(String sql) throws CouponSystemException {
		ArrayList<Coupon> genericCoupons = new ArrayList<Coupon>();
		String sqlCommand = sql;

		Connection con = ConnectionPool.getInstance().getConnection();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sqlCommand);
			System.out.println("CouponGenericGetter-"+sqlCommand);
			while (rs.next()) {
				Coupon coupon = new Coupon();

				coupon.setId(rs.getInt("id"));
				coupon.setCompanyId(rs.getInt("company_id"));
				coupon.setCategory(rs.getInt("category_id"));
				coupon.setTitle(rs.getString("title"));
				coupon.setDescription(rs.getString("description"));
				coupon.setStartDate(rs.getDate("start_Date"));
				coupon.setEndDate((rs.getDate("end_Date")));
				coupon.setAmount(rs.getInt("amount"));
				coupon.setPrice(rs.getDouble("price"));
				coupon.setImage(rs.getString("image"));

				genericCoupons.add(coupon);
				
			}
			ConnectionPool.getInstance().restoreConnection(con);
		}
		catch (SQLException e) {

			ConnectionPool.getInstance().restoreConnection(con);
			throw new com.couponManagmentSystem.couponSystemExceptions.CouponSystemException(
					"Failed to get all Coupons command- "+sql,e);
			
		} finally {

			ConnectionPool.getInstance().restoreConnection(con);
		}
		return genericCoupons;

	}
	@Override
	public Coupon getCouponByTitle(String title) throws CouponSystemException {
		return couponGenericGetter("select * from coupons where title='" + title + "'");
	}
	@Override
	public Coupon couponGenericGetter(String sql) throws CouponSystemException {
		Connection con = ConnectionPool.getInstance().getConnection();
		try {
			Coupon coupon = new Coupon();
			Statement stmt = con.createStatement();
			ResultSet rs;

			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				coupon.setId(rs.getInt("id"));
				coupon.setCompanyId(rs.getInt("company_id"));
				coupon.setCategoryId(rs.getInt("category_id"));
				coupon.setTitle(rs.getString("title"));
				coupon.setDescription(rs.getString("description"));
				coupon.setStartDate((rs.getDate("start_Date")));
				coupon.setEndDate((rs.getDate("end_Date")));
				coupon.setAmount(rs.getInt("amount"));
				coupon.setPrice(rs.getDouble("price"));
				coupon.setImage(rs.getString("image"));
			}

			ConnectionPool.getInstance().restoreConnection(con);
			return coupon;
		} catch (Exception e) {
			e.printStackTrace();
			ConnectionPool.getInstance().restoreConnection(con);
			throw new CouponSystemException("Failed to get Coupon (from generic getter)",e);
		}
		finally {
			ConnectionPool.getInstance().restoreConnection(con);
		}
	}
	@Override
	public List<Coupon> getAllCompanyCouponsByCategory(int companyId,Category category) throws CouponSystemException {
		String sqlGetAllCompanyCouponsByCategory = "SELECT * FROM coupons where category_id='" 
	+category.ordinal()+ "' and company_id='"
				+ companyId+"'";
		System.out.println("from getAllCompanyCouponsByCategory:"+sqlGetAllCompanyCouponsByCategory);
		return CouponGenericGetter(sqlGetAllCompanyCouponsByCategory);
	}
	//this method check if the customer already bought this coupon.
	@Override
	public boolean isCustomerCouponExist(Customer customer,Coupon coupon) throws CouponSystemException {
	return sqlCustomerVsCouponsExist("select * from customers_vs_coupons where customer_id='"
	+customer.getId()+"' and coupon_id='"+coupon.getId()+"'");
	}
	
	public boolean sqlCustomerVsCouponsExist(String sql) throws CouponSystemException {
		Connection con = ConnectionPool.getInstance().getConnection();
		try {
			
			Statement stmt = con.createStatement();
			ResultSet rs;
			int customer_id = 0;
			int coupon_id = 0;
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				customer_id=(rs.getInt("customer_id"));
				coupon_id=(rs.getInt("coupon_id"));

			}
			
			ConnectionPool.getInstance().restoreConnection(con);
			if (customer_id!=0 && coupon_id!=0 )
				return true;
			else
			return false;
			
		} catch (Exception e) {
			e.printStackTrace();
			ConnectionPool.getInstance().restoreConnection(con);
			throw new CouponSystemException("Failed to get Coupon (from generic getter)",e);
		}
		finally {
			ConnectionPool.getInstance().restoreConnection(con);
		}
	}
	@Override
	public List<String> categoriesGetter() throws CouponSystemException {
		Connection con = ConnectionPool.getInstance().getConnection();
		try {
			ArrayList<String> categories=new ArrayList<>();
			Statement stmt = con.createStatement();
			ResultSet rs;

			rs = stmt.executeQuery("SELECT * FROM categories");
			while (rs.next()) {
				categories.add(rs.getString("name"));
			}

			ConnectionPool.getInstance().restoreConnection(con);
			return categories;
		} catch (Exception e) {
			e.printStackTrace();
			ConnectionPool.getInstance().restoreConnection(con);
			throw new CouponSystemException("Failed to get Categories (from categories getter)",e);
		}
		finally {
			ConnectionPool.getInstance().restoreConnection(con);
		}
	}

	@Override
	public ArrayList<Coupon> getAllCouponsByCategoryId(int categoryId) throws CouponSystemException {
		return (ArrayList<Coupon>) CouponGenericGetter("select * from coupons where category_id='" + categoryId+ "'");
	}

	@Override
	public List<Coupon> getCompanyCouponByTitle(int companyId, String title) throws CouponSystemException {
		return (ArrayList<Coupon>) CouponGenericGetter("select * from coupons where title='" + title+ "' and company_id='"+companyId+"'");
	}
}
