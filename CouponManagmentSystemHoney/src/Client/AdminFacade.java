package Client;

import java.util.ArrayList;
import java.util.List;

import com.couponManagmentSystem.Beans.Company;
import com.couponManagmentSystem.Beans.Coupon;
import com.couponManagmentSystem.Beans.Customer;
import com.couponManagmentSystem.Dao.CompanyDBDAO;
import com.couponManagmentSystem.Dao.CouponDBDAO;
import com.couponManagmentSystem.Dao.CustomerDBDAO;
import com.couponManagmentSystem.couponSystemExceptions.CouponSystemException;

public class AdminFacade extends ClientFacade {
	private ClientType clientType = ClientType.ADMINISTRATOR;
	private String email = "admin@admin";
	private String password = "admin";
	private CompanyDBDAO companyDao = new CompanyDBDAO();
	private CouponDBDAO couponDao = new CouponDBDAO();
	private CustomerDBDAO customerDao = new CustomerDBDAO();

	public void updateCompany(Company company) throws CouponSystemException {
		Company dbCompany = companyDao.getCompany(company.getId());
		if ((company.getName().equals(dbCompany.getName())) && (company.getId() == dbCompany.getId())) {
			companyDao.updateCompany(company);
		} else {
			throw new CouponSystemException("can't update company because the name or ID is already exist!"
					+ "dbCompan-"+dbCompany+" company-"+company);
		}

	}

	public void deleteCompany(int companyId) throws CouponSystemException {
		List<Coupon> coupons = couponDao.getAllCompanyCoupons(companyId);
		if (coupons != null) {
			for (Coupon coupon : coupons) {
				customerDao
						.sqlVoidExecuter("delete from customers_vs_coupons where coupon_id='" + coupon.getId() + "'");
				couponDao.removeCoupon(coupon.getId());

			}
		}
		companyDao.deleteCompany(companyId);
	}

	public boolean login(String email, String password) {
		return (email == this.email) && (password == this.password);
	}

	public ClientType getClientType() {
		return clientType;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public CompanyDBDAO getCompanyDao() {
		return companyDao;
	}

	public void addCompany(Company company) throws CouponSystemException {
		if (companyDao.getCompanyByEmail((company.getEmail())) == null
				|| (companyDao.getCompanyByName(company.getName()) == null)) {
			throw new CouponSystemException("Company Already Exist!");
		} else
			companyDao.addCompany(company);

	}

	@Override
	public String toString() {
		return "AdminFacade [clientType=" + clientType + ", email=" + email + ", password=" + password + ", companyDao="
				+ companyDao + "]";
	}

	public List<Company> getAllCompanies() throws CouponSystemException {
		return companyDao.getAllCompanies();
	}

	public Company getOneCompany(int id) throws CouponSystemException {
		return companyDao.getCompany(id);
	}

	public void addCustomer(Customer customer) throws CouponSystemException {
		if (customer.getId() != 0 && (customerDao.getCustomer(customer.getId()).getEmail() == customer.getEmail())) {
			throw new CouponSystemException("cant add customer with the same email");
		} else {
			customerDao.addCustomer(customer);
		}
		if (customer.getCoupons() != null) {
			for (Coupon coupon : customer.getCoupons()) {
				customerDao.addCustomerCoupon(coupon.getId(), customer.getId());
			}
		}

	}

	public AdminFacade() {
		super();
	}

	public AdminFacade(ClientType clientType, String email, String password, CompanyDBDAO companyDao,
			CouponDBDAO couponDao, CustomerDBDAO customerDao) {
		super();
		this.clientType = clientType;
		this.email = email;
		this.password = password;
		this.companyDao = companyDao;
		this.couponDao = couponDao;
		this.customerDao = customerDao;
	}

	public void updateCustomer(Customer customer) throws CouponSystemException {
		System.out.println("check if customer exist");
		if ((customerDao.getCustomer(customer.getId()) != null)
				|| (customerDao.getCustomer(customer.getId()).getId() != 0)) {
			System.out.println("customer exist");
			customerDao.updateCustomer(customer);
		}
	}

	public void deleteCustomer(int customerId) throws CouponSystemException {
		System.out.println("delete Customer from customers vs coupons");
		customerDao.deleteCustomerFromCustomersVSCoupons(customerId);
		System.out.println("delete Customer from DB");
		customerDao.deleteCustomer(customerId);
		System.out.println("finished delete customer coupons and customer");
	}

	public List<Customer> getAllCustomers() throws CouponSystemException {
		return customerDao.getAllCustomers();

	}

	public Customer getOneCustomer(int customerId) throws CouponSystemException {
		return customerDao.getCustomer(customerId);
	}

	public Customer getCustomerByEmailPassword(String email, String password) throws CouponSystemException {
		return customerDao.getCustomerByEmailPassword(email, password);
	}

	public Company getCompanyByEmailAndPassword(String email, String password) throws CouponSystemException {
		return companyDao.getCompany(email, password);
	}
	
	public List<String> getCategories() throws CouponSystemException {
		return couponDao.categoriesGetter();
	}
}
