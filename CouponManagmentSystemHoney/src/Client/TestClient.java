package Client;

import java.beans.ConstructorProperties;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.couponManagmentSystem.Beans.Category;
import com.couponManagmentSystem.Beans.Company;
import com.couponManagmentSystem.Beans.Coupon;
import com.couponManagmentSystem.Beans.Customer;
import com.couponManagmentSystem.Dao.CompaniesDAO;
import com.couponManagmentSystem.Dao.CompanyDBDAO;
import com.couponManagmentSystem.Dao.ConnectionPool;
import com.couponManagmentSystem.Dao.CouponDBDAO;
import com.couponManagmentSystem.Dao.CustomerDBDAO;
import com.couponManagmentSystem.couponSystemExceptions.CouponSystemException;

public class TestClient {
	public static void main(String[] args) throws CouponSystemException, InterruptedException, SQLException {
		testAll();

	}

	public static void testAll() throws CouponSystemException, InterruptedException, SQLException {
//		CouponExpirationDailyJob job = new CouponExpirationDailyJob();
		try {
//			CouponDBDAO couponDao = new CouponDBDAO();
//			System.out.println("from test " + couponDao.getAllExpiredCoupons().toString());
//			Thread couponJob = new Thread(job);
//			job.setThread(couponJob); // give the runnable a reference to the thread
//			couponJob.start();
//
////			new Coupon(categoryId, title, description, amount, price, image, companyId) this is the model to build a coupon
////			new Company(name, email, password) this is the model to build new company
//			AdminFacade admin = (AdminFacade) LoginManager.getInstance().login("admin@admin", "admin", // login as
//																										// administrator
//					ClientType.ADMINISTRATOR);
//			System.out.println("admin logged in");
//			ArrayList<Coupon> customerCoupons = new ArrayList<>(); // make a list of coupons
//			customerCoupons.add(new Coupon(3, "Sprot in maldives", "Kite Surfing Vacation", 5, 5300, "no image", 4));// add
//																														// coupons
//																														// to
//																														// the
//																														// db
//			customerCoupons.add(new Coupon(3, "Sprot in Londos", "Sky Vacation", 6, 3020, "no image", 3));
//			System.out.println("try to add customer");
//			Customer customerNew = new Customer("Customer3", "Tester3", "tester3", "123456");
//			System.out.println("admin try to add customer");
//			admin.addCustomer(customerNew);// add customer to db
//			Customer customerToUpdate = admin.getCustomerByEmailPassword("tester3", "123456");// update a customer
//			customerToUpdate.setEmail("Tester@test.com");
//			admin.updateCustomer(customerToUpdate);
//			System.out
//					.println("try to delete customer " + admin.getCustomerByEmailPassword("Tester@test.com", "123456"));
//			// add company to db
//			Company companyToUpdate = new Company("new1", "new@g1", "1232");
//			System.out.println("try to add company " + companyToUpdate);
//			admin.addCompany(companyToUpdate);
//			companyToUpdate = admin.getCompanyByEmailAndPassword(companyToUpdate.getEmail(),
//					companyToUpdate.getPassword());// get the information (id) from db
//			companyToUpdate.setEmail("updated@update");
//			System.out.println("company to update-" + companyToUpdate);
//			admin.updateCompany(companyToUpdate);
//			System.out.println(
//					"try to delete company " + admin.getCustomerByEmailPassword("updated@update", "1232").getId());
//			admin.deleteCompany(companyToUpdate.getId()); // delete the company
//			admin.addCustomer(new Customer("test", "lastnameTest", "Tester@test.com", "321"));// add customer to db
//			Customer deleteTester = admin.getCustomerByEmailPassword("Tester@test.com", "321");// get information from
//																								// db
//			System.out.println( // print the information about the customer
//					admin.getCustomerByEmailPassword(deleteTester.getEmail(), deleteTester.getPassword()).toString());
//			admin.deleteCustomer( // delete the customer
//					admin.getCustomerByEmailPassword(deleteTester.getEmail(), deleteTester.getPassword()).getId());
//			admin.addCompany(new Company("HoneyTest", "emailtest", "1234")); // add new company by admin
//			Company testCompany = admin.getCompanyByEmailAndPassword("emailtest", "1234");
//			CompanyFacade companyFacade = (CompanyFacade) LoginManager.getInstance().login("emailtest", "1234",
//					ClientType.COMPANY);// login as company
//			companyFacade.addCoupon(new Coupon(3, "Sprot in Londos", "Sky Vacation", 6, 3020, "no image", 3));// add
//																												// coupons
//			companyFacade
//					.addCoupon(new Coupon(3, "Sprot in maldives", "Kite Surfing Vacation", 5, 5300, "no image", 4));
//			System.out.println(companyFacade.getCompanyCoupons());// print all company coupons
//			Coupon updatedByCompany = companyFacade.getCompanyCouponBytitle("Sprot in maldives");
//			updatedByCompany.setAmount(50); // change the amount of this coupon
//			updatedByCompany.setCategory(Category.ELECTRICITY.ordinal()); // change the category
//			companyFacade.updateCoupon(updatedByCompany);// update the coupon
//			// add companies by admin
//			admin.addCompany(new Company("Mega", "mega@mega.com", "a1234"));
//			admin.addCompany(new Company("Profit", "profit@pro.com", "1234p"));
//			admin.addCompany(new Company("Bug", "bug@Bug.com", "b1234"));
//			admin.addCompany(new Company("BBB", "BBB@bbb.com", "3b56"));
//			admin.addCompany(new Company("Rc", "Rc@gmail.com", "1234"));
//			System.out.println(admin.getAllCompanies().toString());// print all companies
////			new Coupon(categoryId, title, description, endDate, amount, price, image, companyId)
//			CompanyFacade company = (CompanyFacade) LoginManager.getInstance().login("Rc@gmail.com", "1234",
//					ClientType.COMPANY);
//			LocalDate endDate = LocalDate.of(2020, 10, 12);
//			LocalDate startDate = LocalDate.now();
//			Coupon galaxy8 = new Coupon(Category.ELECTRICITY.ordinal(), "Galaxy S8", "Best cellular phone",
//					java.sql.Date.valueOf(startDate), java.sql.Date.valueOf(endDate), 5, 3590, "no", company.companyId);
//			System.out.println(galaxy8);
//			Coupon car = new Coupon(Category.SPORT.ordinal(), "Ford Mustang", "Cool car",
//					java.sql.Date.valueOf(startDate), java.sql.Date.valueOf(endDate), 3, 50000, "no",
//					company.companyId);
//			System.out.println(car);
//			company.addCoupon(car);
//			company.addCoupon(galaxy8);
//			galaxy8.setAmount(500);
//			galaxy8.setTitle("Test");
//			Coupon dbCoupon = company.getCompanyCouponBytitle("Test");
//			dbCoupon.setDescription("Description");
//			dbCoupon.setAmount(29);
//			dbCoupon.setCategory(2);
//			LocalDate endDate1 = LocalDate.of(2019, 10, 12);
//			dbCoupon.setEndDate(java.sql.Date.valueOf(endDate1));
//			company.updateCoupon(dbCoupon);
//			company.deleteCoupon(dbCoupon.getId());
//			System.out.println(company.getCompanyCoupons());// print all company coupons
//			System.out.println(company.getCompanyCoupons(Category.FOOD));// print company coupons with category "FOOD"
//			CustomerFacade customer = (CustomerFacade) LoginManager.getInstance().login("Zim@gmail.com", "1234",
//					ClientType.CUSTOMER); // add customer by admin
//			customer.purchase(dbCoupon);
//			customer.purchase(galaxy8);
//			customer.purchase(car);
//			System.out.println(customer.getCustomerCoupon());// print all customer coupons
//			System.out.println(customer.getCustomerCoupons(Category.ELECTRICITY));// print all customer coupons with
//																					// category "ELECTRICITY"
//			System.out.println(customer.getCustomerCoupons((double) 10000));// prin all customer coupons with price
//																			// lower than 10000
//			System.out.println(customer.getCustomerDetails());// print customer detailes
//			
////			
//			java.sql.Date startDate1 = java.sql.Date.valueOf(LocalDate.of(2020, 10, 01));
//			java.sql.Date endDate1 = java.sql.Date.valueOf(LocalDate.of(2020, 10, 12));
//			
//			AdminFacade adminfacade = (AdminFacade) LoginManager.getInstance().login("admin@admin", "admin",
//					ClientType.ADMINISTRATOR);
//			Company company = new Company("testCompany", "company@company.com", "12345");
//			adminfacade.addCompany(company);
//			System.out.println("Company from DB-"
//					+ adminfacade.getCompanyByEmailAndPassword(company.getEmail(), company.getPassword()));
//			Coupon couponTest = new Coupon(Category.ELECTRICITY.ordinal(), "Test for purchase", "for test", 20, 3000,
//					"no", company.getId());
////			couponTest.setStartDate(startDate1);
////			couponTest.setEndDate(endDate1);
//			CompanyFacade companyFacade = (CompanyFacade) LoginManager.getInstance().login(company.getEmail(),
//					company.getPassword(), ClientType.COMPANY);
//			companyFacade.addCoupon(couponTest);
//			System.out.println("Coupon test=" + couponTest);
//			System.out.println("Coupon from DB-" + companyFacade.getCompanyCouponBytitle(couponTest.getTitle()));
//			Customer client = new Customer("tester", "Coupon purchase", "tester@gmail.com", "1234");
//			adminfacade.addCustomer(client);
//			CustomerFacade customerfFacade = (CustomerFacade) LoginManager.getInstance().login("tester@gmail.com",
//					"1234", ClientType.CUSTOMER);
//			System.out.println("started purchase");
//			System.out.println("coupon is" + companyFacade.getCompanyCouponBytitle(couponTest.getTitle()));
//			customerfFacade.purchase(companyFacade.getCompanyCouponBytitle(couponTest.getTitle()));
//			System.out.println("finish purchase coupon");
			System.out.println("from test is customerVsCoupon exist "+new CouponDBDAO().sqlCustomerVsCouponsExist(
					
					"select * from customers_vs_coupons where customer_id='1' and coupon_id='24'"));
			CustomerFacade facade=new CustomerFacade();
			Coupon coupontest=new CouponDBDAO().getCoupon(50);
			coupontest.setAmount(0);
			new CouponDBDAO().updateCoupon(coupontest);
			facade.purchase(coupontest);
		} catch (Exception e) {
			e.printStackTrace();
			throw new CouponSystemException("Test Failed ", e);
		} finally {
//			Thread.sleep(1000 * 35);
//			if (job != null) {
//				job.stopJob();
//				job.getThread().join();
//				ConnectionPool.getInstance().closeAllConnections();
//			}
		}

	}
}