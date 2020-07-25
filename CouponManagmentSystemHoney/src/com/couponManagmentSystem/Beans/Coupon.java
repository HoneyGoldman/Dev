package com.couponManagmentSystem.Beans;

import java.sql.Date;

/**
 *this class define what Coupon object is 
 */
public class Coupon {
	private int id;
	private int categoryId;
	public Coupon(int categoryId, String title, String description, Date endDate, int amount, double price,
			String image, int companyId) {
		super();
		this.categoryId = categoryId;
		this.title = title;
		this.description = description;
		this.endDate = endDate;
		this.amount = amount;
		this.price = price;
		this.image = image;
		this.companyId = companyId;
	}
 
	private String title;
	private String description;
	private java.sql.Date startDate;
	private java.sql.Date endDate;
	private int amount;
	private double price;
	private String image;
	private int companyId;

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public Coupon(int id, int companyID, int categoryId, String title) {
		super();
		this.id = id;
		this.categoryId = categoryId;
		this.title = title;
	}


	public Coupon(int id, int categoryId, String title, String description, java.sql.Date startDate,
			java.sql.Date endDate, int amount, double price, String image) {
		super();
		this.id = id;
		this.categoryId = categoryId;
		this.title = title;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
		this.amount = amount;
		this.price = price;
		this.image = image;
	}

	public Coupon( int category, String title, String description, java.sql.Date startDate,
			java.sql.Date endDate, int amount, double price, String image) {
		super();
		this.categoryId = category;
		this.title = title;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
		this.amount = amount;
		this.price = price;
		this.image = image;
	}

	public Coupon() {
		super();
	}

	

	public Coupon(int categoryId, String title, String description, int amount, double price, String image,
			int companyId) {
		super();
		this.categoryId = categoryId;
		this.title = title;
		this.description = description;
		this.amount = amount;
		this.price = price;
		this.image = image;
		this.companyId = companyId;
	}

	

	public Coupon(int categoryId, String title, String description, Date startDate, Date endDate, int amount,
			double price, String image, int companyId) {
		super();
		this.categoryId = categoryId;
		this.title = title;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
		this.amount = amount;
		this.price = price;
		this.image = image;
		this.companyId = companyId;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
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
		Coupon other = (Coupon) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Coupon [id=" + id + ", categoryId=" + categoryId + ", title=" + title + ", description=" + description
				+ ", startDate=" + startDate + ", endDate=" + endDate + ", amount=" + amount + ", price=" + price
				+ ", image=" + image + ", companyId=" + companyId + "]";
	}



	/**
	 * sets the coupon's category's id.
	 * 
	 * @param categoryId
	 */
	public void setCategory(int categoryId) {
		this.categoryId = categoryId;
	}

	/**
	 * returns the coupon's title.
	 * 
	 * @return title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * sets the coupon's title.
	 * 
	 * @param title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * returns the coupon's description.
	 * 
	 * @return description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * sets the coupons description.
	 * 
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * returns coupon's start date.
	 * 
	 * @return
	 */
	public java.sql.Date getStartDate() {
		return startDate;
	}

	/**
	 * sets coupon's start date.
	 * 
	 * @param startDate
	 */
	public void setStartDate(java.sql.Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * returns coupon's end date.
	 * 
	 * @return
	 */
	public java.sql.Date getEndDate() {
		return endDate;
	}

	/**
	 * sets coupon's end date.
	 * 
	 * @param endDate
	 */
	public void setEndDate(java.sql.Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * returns coupon's amount.
	 * 
	 * @return
	 */
	public int getAmount() {
		return amount;
	}

	/**
	 * sets coupon's amount.
	 * 
	 * @param amount
	 */
	public void setAmount(int amount) {
		this.amount = amount;
	}

	/**
	 * returns coupon's price.
	 * 
	 * @return
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * sets coupon's price.
	 * 
	 * @param price
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * returns coupons image.
	 * 
	 * @return price
	 */
	public String getImage() {
		return image;
	}

	/**
	 * set the coupon's image
	 * 
	 * @param image
	 */
	public void setImage(String image) {
		this.image = image;
	}

}

