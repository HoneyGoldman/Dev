package CouponManagmentSystemWS.BuissnesDelegate;

import java.util.Date;

import org.apache.derby.tools.sysinfo;

import com.fasterxml.jackson.databind.deser.impl.JavaUtilCollectionsDeserializers;

import Beans.Description;
import Beans.Income;

public class Test {

	public static void main(String[] args) {
		try {
		BuissnesDelegate buissnes=new BuissnesDelegate();
		Date date=new Date(12, 10, 1994);
		Income income=new Income("Company", date, Description.COMPANY_NEW_COUPON,100,2);
		System.out.println(income);
		buissnes.storeIncome(income);
		Income income2=new Income("Customer", date, Description.CUSTOMER_PURCHASE,50,2);
		buissnes.storeIncome(income2);
		Income income3=new Income("Customer", date, Description.CUSTOMER_PURCHASE,50,2);
		buissnes.storeIncome(income3);
		System.out.println("all income"+ buissnes.getAllIncome());
		System.out.println("all income by customer"+ buissnes.getAllIncomeByCustomer(1));
		System.out.println("income by company"+buissnes.getAllIncomeByCompany(1));
	
	}
		catch(Exception e) {
			e.printStackTrace();
			
		}
}}
