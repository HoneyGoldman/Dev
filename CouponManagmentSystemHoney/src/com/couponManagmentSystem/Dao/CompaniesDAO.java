package com.couponManagmentSystem.Dao;


import java.util.ArrayList;

import com.couponManagmentSystem.Beans.Category;
import com.couponManagmentSystem.Beans.Company;
import com.couponManagmentSystem.couponSystemExceptions.CouponSystemException;

/**
 * an interface that define what is the actions we can do to the Companies in
 * the DB.
 */
public interface CompaniesDAO {
	/**
	 * the method isExist check if the company is exists in the database according
	 */
	public boolean isCompanyExists(String email, String password) throws CouponSystemException;

	/**
	 * addCompany is a method that used to add a company to the database.
	 */
	
	public void addCompany(Company company) throws CouponSystemException;

	/**
	 * updateCompany is a method that used to update company from the company table
	 */
	public void updateCompany(Company company) throws CouponSystemException;

	/**
	 * deleteCompany is a method that used to delete a company by company's id
	 * number.
	 */
	public void deleteCompany(int companyID) throws CouponSystemException;

	/**
	 * getAllCompany is a method that can be use to get all the companies from the
	 * database.
	 */

	public ArrayList<Company> getAllCompanies() throws CouponSystemException;

	/**
	 * the method getCompny allows to get specified company according to a company's
	 * id number.
	 */

	public Company getCompany(int companyID) throws CouponSystemException;

	/**
	 * the method getCompny gives specified company according to a company's email.
	 */
	public Company getCompany(String email) throws CouponSystemException;
	
	public Company getCompany(String email,String password) throws CouponSystemException;
	/**
	 * return true if the company exist- use for login 
	 */

	public Boolean login(String email,String password) throws CouponSystemException;

	Company companyGetter(String sql) throws CouponSystemException;

	Company getCompanyByEmail(String email) throws CouponSystemException;

	Company getCompanyByName(String name) throws CouponSystemException;

	int getCategory(Category category) throws CouponSystemException;
	
}
