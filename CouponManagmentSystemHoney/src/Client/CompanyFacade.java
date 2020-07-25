package Client;

import java.util.List;

import com.couponManagmentSystem.Beans.Category;
import com.couponManagmentSystem.Beans.Company;
import com.couponManagmentSystem.Beans.Coupon;
import com.couponManagmentSystem.Beans.Customer;
import com.couponManagmentSystem.Dao.CompaniesDAO;
import com.couponManagmentSystem.Dao.CompanyDBDAO;
import com.couponManagmentSystem.Dao.CouponDAO;
import com.couponManagmentSystem.Dao.CouponDBDAO;
import com.couponManagmentSystem.Dao.CustomerDAO;
import com.couponManagmentSystem.Dao.CustomerDBDAO;
import com.couponManagmentSystem.couponSystemExceptions.CouponSystemException;

public class CompanyFacade extends ClientFacade {
	CouponDAO couponDao = new CouponDBDAO();
	CompaniesDAO companyDao = new CompanyDBDAO();
	CustomerDAO customerDao = new CustomerDBDAO();
	private Company company;
	int companyId;

	@Override
	public boolean login(String email, String password) throws CouponSystemException {
		if (companyDao.isCompanyExists(email, password)) {
			company = companyDao.getCompany(email, password);
			this.companyId = company.getId();
			return true;
		} else {
			return false;
		}
	}

	public void addCoupon(Coupon coupon) throws CouponSystemException {
		System.out.println("from add coupon company Facade-"+coupon);
System.out.println("company id-"+companyId);
		if (coupon.getTitle()!=null) {
		if (couponDao.getCouponByTitle(coupon.getTitle()).getTitle()!=null) {
		if (couponDao.getCouponByTitle(coupon.getTitle()).getTitle().equals(coupon.getTitle())) {
			System.out.println("company Id 1="+coupon.getCompanyId()+" title "+coupon.getTitle());
			System.out.println("company id 2="+couponDao.getCouponByTitle(coupon.getTitle()).getCompanyId()+" title "+couponDao.getCouponByTitle(coupon.getTitle()).getTitle());	
		if (coupon.getCompanyId() == couponDao.getCouponByTitle(coupon.getTitle()).getCompanyId()) {
			throw new CouponSystemException("Cant add coupon with the same title for the same company");
		}
		}
		}
			else {

		System.out.println("from create coupon company Facade");
		coupon.setCompanyId(companyId);
		couponDao.createCoupon(coupon);
		}
		}
		
	else {
		System.out.println("coupon title is null "+coupon.getTitle());
	}
	}
	public void updateCoupon(Coupon coupon) throws CouponSystemException {
		System.out.println(couponDao.getCoupon(coupon.getId()).getCompanyId() + " Coupon 1 company id");
		System.out.println(coupon.getCompanyId() + " Coupon 2 company id");

		if (couponDao.getCoupon(coupon.getId()).getCompanyId() == coupon.getCompanyId()) {
			couponDao.updateCoupon(coupon);
		} else {
			throw new CouponSystemException("cant update Company Id in Coupon");
		}
	}

	public void deleteCoupon(int couponId) throws CouponSystemException {
		couponDao.removeCoupon(couponId);
//		try {
		customerDao.deleteCouponForAllCustomers(couponId);

//		} catch (Exception e) {
//		
//		}
	}

	public List<Coupon> getCompanyCoupons() throws CouponSystemException {
		return couponDao.getAllCompanyCoupons(companyId);
	}

	public List<Coupon> getCompanyCoupons(Category category) throws CouponSystemException {
		return couponDao.getAllCompanyCouponsByCategory(companyId, category);
	}

	public List<Coupon> getCompanyCoupons(Double maxprice) throws CouponSystemException {
		return couponDao.getAllCompanyCouponsByMaxPrice(company.getId(), maxprice);
	}

	public Coupon getCompanyCouponBytitle(String title) throws CouponSystemException {
		return couponDao.getCouponByTitle(title);
	}

	public Company getCompanyDetails() {
		return company;
	}

	public Coupon getCoupon(int id) throws CouponSystemException {
		return couponDao.getCoupon(id);

	}
	public List<Coupon> getCompanyCouponByTitle(String title) throws CouponSystemException {
		return couponDao.getCompanyCouponByTitle(companyId,title);

	}
}
