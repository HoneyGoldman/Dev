package Client;

import java.util.Date;
import java.util.List;

import com.couponManagmentSystem.Beans.Category;
import com.couponManagmentSystem.Beans.Coupon;
import com.couponManagmentSystem.Beans.Customer;
import com.couponManagmentSystem.Dao.CompaniesDAO;
import com.couponManagmentSystem.Dao.CompanyDBDAO;
import com.couponManagmentSystem.Dao.CouponDBDAO;
import com.couponManagmentSystem.Dao.CustomerDBDAO;
import com.couponManagmentSystem.couponSystemExceptions.CouponSystemException;

public class CustomerFacade extends ClientFacade {
	CustomerDBDAO customerDao = new CustomerDBDAO();
	CouponDBDAO couponDao = new CouponDBDAO();
	private Customer customer = new Customer();

	@Override
	public boolean login(String email, String password) throws CouponSystemException {
		if (customerDao.isCustomerExists(email, password)) {
			customer = customerDao.getCustomerByEmailPassword(email, password);
			return true;
		} else {
			throw new CouponSystemException("wrong credentials!");
		}
	}

	public void purchase(Coupon coupon) throws CouponSystemException {
		if (couponDao.isCustomerCouponExist(customer, coupon)) {
			throw new CouponSystemException("this customer already bought this coupon customer=" + customer.toString()
					+ "Coupon=" + coupon.toString());
		} 
		else {
			System.out.println("coupon amount is "+coupon.getAmount());
			if (coupon.getAmount() == 0) {
				throw new CouponSystemException("Coupon Amount is Zero!");
			} 
			
		else {
				if (coupon.getEndDate()==null) {
					System.out.println("from add customer coupon");
					customerDao.addCustomerCoupon(coupon.getId(), customer.getId());
					coupon.setAmount(coupon.getAmount() - 1);
					couponDao.updateCoupon(coupon);
				} 
				else {
					if (new Date().after((coupon.getEndDate()))) {
						throw new CouponSystemException("Coupon is too old");
					} else {
						System.out.println("from add customer coupon");
						customerDao.addCustomerCoupon(coupon.getId(), customer.getId());
						coupon.setAmount(coupon.getAmount() - 1);
						couponDao.updateCoupon(coupon);

					}
				}

			}
		}
	}

	public CustomerFacade() {
		super();
	}

	public CustomerFacade(Customer customer) {
		super();
		this.customer = customer;
	}

	public List<Coupon> getCustomerCoupon() throws CouponSystemException {
		return customerDao.getAllCustomerCoupons(customer.getId());
	}

	public List<Coupon> getCustomerCouponsByCategory(int categoryId) throws CouponSystemException {
		System.out.println("from get customer coupon by category customer id=" + this.customer.getId() + " category id="
				+ categoryId);
		;
		return customerDao.getAllCustomerCouponsByCategory(categoryId, this.customer.getId());
	}

	public List<Coupon> getCustomerCoupons(Double maxprice) throws CouponSystemException {
		return customerDao.getAllCustomerCouponsByMaxPrice(maxprice, customer.getId());

	}

	public Customer getCustomerDetails() {
		return customer;
	}
	
	public String getCategory(int categoryId) throws CouponSystemException {
		CompaniesDAO companyDao =new CompanyDBDAO();
		return CommonFacade.getCategory(categoryId);
		
	}
	public List<Coupon> getAllCoupons() throws CouponSystemException{
		return couponDao.getAllCoupons();
	}
	
	public List<Coupon> getCustomerCoupons(int customerId) throws CouponSystemException{
		return customerDao.getAllCustomerCoupons(customerId);
	}
	
}
