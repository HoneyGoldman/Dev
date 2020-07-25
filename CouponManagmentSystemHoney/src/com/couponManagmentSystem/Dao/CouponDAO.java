package com.couponManagmentSystem.Dao;

import java.util.ArrayList;
import java.util.List;

import com.couponManagmentSystem.Beans.Category;
import com.couponManagmentSystem.Beans.Coupon;
import com.couponManagmentSystem.Beans.Customer;
import com.couponManagmentSystem.couponSystemExceptions.CouponSystemException;

/**
 * an interface that define witch action we make in coupons table.
 */
public interface CouponDAO {
	/**
	 * the method isExist check if the coupon is exists in the database according to
	 * the coupon's id number.
	 */
	public boolean isExists(int id) throws CouponSystemException;

	/**
	 * addCoupon is a method that allows to add a coupon to the database.
	 */

	public void createCoupon(Coupon coupon) throws CouponSystemException;

	/**
	 * update a coupon in the database.
	 */
	public void updateCoupon(Coupon Coupon) throws CouponSystemException;

	/**
	 * deleteCoupon is a method that allows to delete a coupon according a coupon's
	 * id number.
	 */
	public void removeCoupon(int id) throws CouponSystemException;

	/**
	 * getAllCoupon is a method that can be use to get all the coupons from the
	 * database. the method gets no parameters.
	 */
	public ArrayList<Coupon> getAllCoupons() throws CouponSystemException;

	
	public ArrayList<Coupon> getAllCouponsByCategoryId(int categoryId) throws CouponSystemException;
	/**
	 * the method getCooupon allows to get specified coupon according to a coupon's
	 * id number.
	 */
	public Coupon getCoupon(int CouponId) throws CouponSystemException;

	/**
	 * a method that returns all the coupons from the database under a max price
	 * according to a company id.
	 */

	public List<Coupon> getAllCompanyCouponsByMaxPrice(int companyId, Double price) throws CouponSystemException;

	/**
	 * a method that returns all the coupons in the database according to a type.
	 */
	List<Coupon> getCouponByType(String type)throws CouponSystemException;

	/**
	 * a generic method that get a sql commend and return a list of coupons.
	 */
	public List<Coupon> CouponGenericGetter(String sql) throws CouponSystemException;

	/**
	 * returns all the coupon that expired;
	 */
	public List<Coupon> getAllExpiredCoupons() throws CouponSystemException;

	Coupon getCouponByTitle(String title) throws CouponSystemException;

	Coupon couponGenericGetter(String sql) throws CouponSystemException;

	List<Coupon> getAllCompanyCouponsByCategory(int companyId, Category category) throws CouponSystemException;

	boolean isCustomerCouponExist(Customer customer, Coupon coupon) throws CouponSystemException;

	public List<Coupon> getAllCompanyCoupons(int companyId) throws CouponSystemException;

	List<String> categoriesGetter() throws CouponSystemException;

	List<Coupon> getCompanyCouponByTitle(int companyId, String title) throws CouponSystemException;
}
