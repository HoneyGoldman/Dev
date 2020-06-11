package CouponManagmentSystemWS;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.derby.tools.sysinfo;

import com.couponManagmentSystem.Beans.Company;
import com.couponManagmentSystem.Beans.Customer;

import Client.AdminFacade;
import CouponManagmentSystemWS.BuissnesDelegate.BuissnesDelegate;

@Path("/AdminService")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AdminService {
	
	private BuissnesDelegate buissnes=new BuissnesDelegate();
	@Context
	HttpServletRequest req;

	private AdminFacade getAdminFacde() {
		System.out.println("session is-" +req.getSession().getId());
		System.out.println("admin facade is "+req.getSession().getAttribute("clientFacade"));
		System.out.println("session is-"+req.getSession());
		return (AdminFacade) req.getSession().getAttribute("clientFacade");
	}
	
	//Works
	@Path("addCustomer")
	@POST
	public Response addCustomer(Customer customer) {
		try {
			System.out.println("from admin service add customer "+customer);
			getAdminFacde().addCustomer(customer);
			return Response.status(Response.Status.OK).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e).build();
		}

	}

	//Works
	@Path("addCompany")
	@POST
	public Response addCustomer(Company company) {
		try {
			getAdminFacde().addCompany(company);
			return Response.status(Response.Status.OK).header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
					.allow("OPTIONS").build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
					.allow("OPTIONS").entity(e).build();
		}
	}
	
	//Works
	@Path("deleteCustomer")
	@DELETE
	public Response deleteCustomer(@QueryParam("customerId")int customerId) {
		try {
			getAdminFacde().deleteCustomer(customerId);
			return Response.status(Response.Status.OK).header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
					.allow("OPTIONS").build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
					.allow("OPTIONS").entity(e).build();
		}

	}

	//Works
	@Path("deleteCompany")
	@DELETE
	public Response deleteCompany(@QueryParam("companyId") int companyId) {
		try {
			getAdminFacde().deleteCompany(companyId);
			return Response.status(Response.Status.OK).header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
					.allow("OPTIONS").build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
					.allow("OPTIONS").entity(e).build();
		}

	}

	// Works
	@Path("getAllCompanies")
	@GET
	public Response getAllCompanies() {
		try {
			System.out.println("from get all comanies admin service " + getAdminFacde().getAllCompanies());
			return Response.status(Response.Status.OK).header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
					.allow("OPTIONS").entity(getAdminFacde().getAllCompanies()).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
					.allow("OPTIONS").entity(e).build();
		}

	}

	// Works
	@Path("getAllCustomers")
	@GET
	public Response getAllCustomers() {
		try {
			System.out.println("from all customers admin service "+getAdminFacde().getAllCustomers());
			return Response.status(Response.Status.OK).header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
					.allow("OPTIONS").entity(getAdminFacde().getAllCustomers()).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
					.allow("OPTIONS").entity(e).build();
		}

	}

	// Works
	@Path("getCompanyByEmailAndPassword")
	@GET
	public Response getCompanyByEmailAndPassword(@QueryParam("email") String email,
			@QueryParam("password") String password) {
		try {
System.out.println("from admin service get company by email and password "+getAdminFacde().getCompanyByEmailAndPassword(email, password));
			return Response.status(Response.Status.OK)
					.entity(getAdminFacde().getCompanyByEmailAndPassword(email, password)).header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
					.allow("OPTIONS").build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
					.allow("OPTIONS").entity(e).build();
		}

	}

	// Works
	@Path("getCustomerByEmailAndPassword")
	@GET
	public Response getCustomerByEmailAndPassword(@QueryParam("email") String email,
			@QueryParam("password") String password) {
		try {

			return Response.status(Response.Status.OK)
					.header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
					.allow("OPTIONS").entity(getAdminFacde().getCustomerByEmailPassword(email, password)).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
					.allow("OPTIONS").entity(e).build();
		}

	}

	// Works
	@Path("getCompany")
	@GET
	public Response getCompany(@QueryParam("companyId") int companId) {
		try {

			return Response.status(Response.Status.OK).header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
					.allow("OPTIONS").entity(getAdminFacde().getOneCompany(companId)).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
					.allow("OPTIONS").entity(e).build();
		}

	}

	// Works
	@Path("getCustomer")
	@GET
	public Response getCustomer(@QueryParam("customerId") int customerId) {
		try {

			return Response.status(Response.Status.OK).header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
					.allow("OPTIONS").entity(getAdminFacde().getOneCustomer(customerId)).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
					.allow("OPTIONS").entity(e).build();
		}

	}

	// Works
	@Path("updateCompany")
	@PUT
	public Response updateCompany(Company company) {
		try {
			getAdminFacde().updateCompany(company);
			return Response.status(Response.Status.OK).header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
					.allow("OPTIONS").build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
					.allow("OPTIONS").entity(e).build();
		}

	}

	// Works
	@Path("updateCustomer")
	@PUT
	public Response updateCustomer(Customer customer) {
		try {
			System.out.println("from update customer-"+customer);
			getAdminFacde().updateCustomer(customer);
			return Response.status(Response.Status.OK).header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
					.allow("OPTIONS").build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
					.allow("OPTIONS").entity(e).build();
		}

	}
	
	//Works
	@Path("logOut")
	@GET
	public Response logOut() {
		try {
			System.out.println("from logout");
			req.getSession().invalidate();
			return Response.status(Response.Status.OK).header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
					.allow("OPTIONS").build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
					.allow("OPTIONS").entity(e).build();
		}
	
}
	
	//Works
	@Path("getIncomeByCompany")
	@GET
	public Response getIncomeByCompany(@QueryParam("companyId") int companyId) {
		try {

			return Response.status(Response.Status.OK).header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
					.allow("OPTIONS").entity(buissnes.getAllIncomeByCompany(companyId)).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
					.allow("OPTIONS").entity(e).build();
		}

	}


	//Works
		@Path("getIncomeByCustomer")
		@GET
		public Response getIncomeByCustomer(@QueryParam("customerId") int customerId) {
			try {

				return Response.status(Response.Status.OK).header("Access-Control-Allow-Origin", "*")
						.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
						.allow("OPTIONS").entity(buissnes.getAllIncomeByCustomer(customerId)).build();
			} catch (Exception e) {
				e.printStackTrace();
				return Response.status(Response.Status.INTERNAL_SERVER_ERROR).header("Access-Control-Allow-Origin", "*")
						.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
						.allow("OPTIONS").entity(e).build();
			}

		}
		
		
		//Works
		@Path("getAllIncome")
		@GET
		public Response getAllIncome() {
			try {

				return Response.status(Response.Status.OK).header("Access-Control-Allow-Origin", "*")
						.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
						.allow("OPTIONS").entity(buissnes.getAllIncome()).build();
			} catch (Exception e) {
				e.printStackTrace();
				return Response.status(Response.Status.INTERNAL_SERVER_ERROR).header("Access-Control-Allow-Origin", "*")
						.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
						.allow("OPTIONS").entity(e).build();
			}

		}
	}
