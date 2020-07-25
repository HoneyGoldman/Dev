package com.couponManagmentSystem.Dao;

import java.util.ArrayList;
import java.util.List;

import com.couponManagmentSystem.Beans.Category;
import com.couponManagmentSystem.Beans.Coupon;
import com.couponManagmentSystem.Beans.Customer;
import com.couponManagmentSystem.couponSystemExceptions.*;

public interface CustomerDAO {
	/**
	 * Check if the customer is exist by mail and password
	 */
	public boolean isCustomerExists(String email, String password) throws CouponSystemException;

	/**
	 * add a new customer as an object
	 */
	public void addCustomer(Customer customer) throws CouponSystemException;

	/**
	 * update a customer as an object.
	 */
	public void updateCustomer(Customer customer) throws CouponSystemException;

	/**
	 * delete customer by customer's id.
	 */
	public void deleteCustomer(int customerID) throws CouponSystemException;

	/**
	 * return a list of all the customer in the database.
	 */
	public ArrayList<Customer> getAllCustomers() throws CouponSystemException;

	/**
	 * return one customer from the database by customer's id.
	 */
	public Customer getCustomer(int customerID) throws CouponSystemException;

	/**
	 * return one customer by customer's name.
	 */
	public Customer getOneCustomer(String name) throws CouponSystemException;

	ArrayList<Coupon> getAllCustomerCoupons(int customerId) throws CouponSystemException;

	ArrayList<Coupon> getCoupons() throws CouponSystemException;

	Customer sqlExecuter(String sql) throws CouponSystemException;

	void addCustomerCoupon(int couponId, int customerId) throws CouponSystemException;

	void sqlVoidExecuter(String sql) throws CouponSystemException;

	void deleteCustomerFromCustomersVSCoupons(int customerId) throws CouponSystemException;

	Customer getCustomerByEmailPassword(String email, String password) throws CouponSystemException;

	void deleteCustomerCoupons(Customer customer) throws CouponSystemException;

	void deleteCustomerCoupon(Customer customer, Coupon coupon) throws CouponSystemException;

	void deleteCouponForAllCustomers(int couponId) throws CouponSystemException;

	List<Customer> getCustomersByCouponId(int couponId) throws CouponSystemException;

	List<Coupon> getAllCustomerCouponsByMaxPrice(Double maxprice, int customerId) throws CouponSystemException;

	Customer sqlExecuterForSelect(String sql) throws CouponSystemException;

	int categoryGetter(Category category) throws CouponSystemException;

	List<Coupon> getAllCustomerCouponsByCategory(int categoryId, int customerId) throws CouponSystemException;

	
}
