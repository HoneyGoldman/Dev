package com.couponManagmentSystem.Dao;

import java.util.ArrayList;

import javax.xml.transform.Templates;

import com.couponManagmentSystem.Beans.Category;
import com.couponManagmentSystem.Beans.Company;
import com.couponManagmentSystem.Beans.Coupon;
import com.couponManagmentSystem.Beans.Customer;
import com.couponManagmentSystem.couponSystemExceptions.CouponSystemException;

import Client.AdminFacade;

import com.couponManagmentSystem.Beans.*;
public class Test {
public static void main(String[] args) {
	try {
//		AdminFacade admin=new AdminFacade();
//		System.out.println(admin.getOneCustomer(4));
//		ConnectionPool connectionPool=null;
//		ConnectionPool con= ConnectionPool.getInstance();
//		Company cocaKola=new Company("test","coca@gmail.com","1234");
//		
//		CompaniesDAO companyDao = new CompanyDBDAO();
//		Category category=Category.RESTAURANT;
//		companyDao.addCompany(cocaKola);
		//companyDao.updateCompany(new Company(1,"coca","coca12@gmail.com","1234"));
	//companyDao.deleteCompany(1);
	//companyDao.addCompany(cocaKola);
//    CouponDBDAO couponDao=new CouponDBDAO();
//	couponDao.createCoupon(new Coupon(1,"megasport", "best coupon",100,50,"no",3));
//	couponDao.createCoupon(new Coupon(1,"sport", "best Sport",20,200,"no",3));
//	couponDao.createCoupon(new Coupon(1,"Nofesh", "best Vacation",3,1200,"no",3));
//	couponDao.createCoupon(new Coupon(1,"sport", "Sport!",220,200,"no",3));
//	couponDao.createCoupon(new Coupon(1,"Fun", "best Vacation",35,1200,"no",3));
//	couponDao.createCoupon(new Coupon(1,"sport", "best Sport",20,2500,"no",3));
//	couponDao.createCoupon(new Coupon(1,"Nofesh", "best Vacation",32,1200,"no",3));
//CustomerDBDAO costome=new CustomerDBDAO();
//ArrayList<Coupon> temp=costome.getAllCustomerCoupons(2);
//System.out.println(temp.toString());
//costome.addCustomerCoupon(17,3);
//System.out.println(category.ordinal());
		CouponDAO dao=new CouponDBDAO();
		System.out.println(dao.getCoupon(24));
	} catch (CouponSystemException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}
}
