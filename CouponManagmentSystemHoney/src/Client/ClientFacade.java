package Client;

import com.couponManagmentSystem.Dao.CompaniesDAO;
import com.couponManagmentSystem.Dao.CompanyDBDAO;
import com.couponManagmentSystem.Dao.CouponDAO;
import com.couponManagmentSystem.Dao.CouponDBDAO;
import com.couponManagmentSystem.Dao.CustomerDAO;
import com.couponManagmentSystem.Dao.CustomerDBDAO;
import com.couponManagmentSystem.couponSystemExceptions.CouponSystemException;

public abstract class ClientFacade {
	private CompaniesDAO companyDAO = new CompanyDBDAO();
	private CustomerDAO customersDAO = new CustomerDBDAO();
	private CouponDAO couponDAO = new CouponDBDAO();
	
	
 public CompaniesDAO getCompanyDAO() {
		return companyDAO;
	}


	public CustomerDAO getCustomersDAO() {
		return customersDAO;
	}


	public CouponDAO getCouponDAO() {
		return couponDAO;
	}


public abstract boolean login(String email,String password) throws CouponSystemException;

}
