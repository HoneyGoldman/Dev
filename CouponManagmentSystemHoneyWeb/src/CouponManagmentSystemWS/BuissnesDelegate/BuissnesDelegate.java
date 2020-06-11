package CouponManagmentSystemWS.BuissnesDelegate;

import java.util.Collection;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import Beans.Income;

public class BuissnesDelegate {
public Client client=ClientBuilder.newClient();
public Income income;
private WebTarget target=client.target("http://localhost:8888/api");

public synchronized Income storeIncome(Income income) {

 Response response=target.path("income").request().put(Entity.json(income));
 System.out.println("income is "+income);
 System.out.println("json is "+Entity.json(income));
 if(response.getStatusInfo()==Response.Status.OK) 
 {
	 return response.readEntity(Income.class);}
 	else {
		System.out.println(response.getStatus());
		throw new RuntimeException("Store income "+income+" failed "+response.getStatusInfo().getReasonPhrase());}
	
	}

public synchronized Collection<Income> getAllIncome() {
	 Response response=target.path("getAllIncomes").request().get();
	 if(response.getStatusInfo()==Response.Status.OK) {
		 return response.readEntity(new GenericType<Collection<Income>>(){});}
		 else {
			 throw new RuntimeException("Store income "+income+" failed");
		 }
} 

public synchronized Collection<Income> getAllIncomeByCustomer(int id) {
	System.out.println("from getAllIncomeByCustomer Buissnes Delegate "+target.path("viewIncomeByCustomer/"+id));
	 Response response=target.path("viewIncomeByCustomer/"+id).request().get();
	 if(response.getStatusInfo()==Response.Status.OK) {
		 return response.readEntity(new GenericType<Collection<Income>>(){});}
		 else {
			 throw new RuntimeException("get income by customer "+id+" failed");
		 }
} 

public synchronized Collection<Income> getAllIncomeByCompany(int id) {
	System.out.println("from getAllIncomeByCompany Buissnes Delegate "+target.path("viewIncomeByCompany/"+id));
	 Response response=target.path("viewIncomeByCompany/"+id).request().get();
	 if(response.getStatusInfo()==Response.Status.OK) {
		 return response.readEntity(new GenericType<Collection<Income>>(){});}
		 else {
			 throw new RuntimeException("get income by company "+id+" failed");
		 }
} 

}
