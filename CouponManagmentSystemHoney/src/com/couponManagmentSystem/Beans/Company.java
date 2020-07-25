package com.couponManagmentSystem.Beans;

import java.util.ArrayList;

/**
 * this class define what Company object is
 */
public class Company {
	private int id;
	private String name;
	private String email;
	private String password;
	private ArrayList<Coupon> coupons;

	public Company(int id, String name, String email, String password) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Company other = (Company) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public Company() {
		super();
	}

	
	public Company(String name, String email, String password, ArrayList<Coupon> coupons) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.coupons = coupons;
	}

	public Company(String name, String email, String password) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
	}

	public void setId(int id) {
		this.id = id;
	}


	public Company(int id, String name, String email, String password, ArrayList<Coupon> coupons) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.coupons = coupons;
	}

	public Company(int id) {
		super();
		this.id = id;
	}

	@Override
	public String toString() {
		return "Company [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", coupons="
				+ coupons + "]";
	}

	public Company(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	/**
	 * get id is a method that returns the id of the company.
	 * 
	 * @return id
	 */
	public int getId() {
		return id;
	}

	/**
	 * getName is a method that returns the company's name.
	 * 
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * setName is a method that set the companay's name.
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * getEmail is a method that returns company's email.
	 * 
	 * @return email.
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * the method setEmail set's the company's email.
	 * 
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * getPassword is a method that return company's password.
	 * 
	 * @return password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * setPassword is a method that sets the company's password.
	 * 
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * getCoupons is a method that returns all company's coupons.
	 * 
	 * @return coupons
	 */
	public ArrayList<Coupon> getCoupons() {
		return coupons;
	}

	/**
	 * setCoupon is a method that set all the company's method.
	 * 
	 * @param coupons
	 */
	public void setCoupons(ArrayList<Coupon> coupons) {
		this.coupons = coupons;
	}

}
