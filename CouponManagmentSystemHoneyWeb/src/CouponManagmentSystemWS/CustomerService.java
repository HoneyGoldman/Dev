package CouponManagmentSystemWS;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.derby.tools.sysinfo;

import com.couponManagmentSystem.Beans.Category;
import com.couponManagmentSystem.Beans.Coupon;
import com.couponManagmentSystem.Beans.Customer;
import com.couponManagmentSystem.couponSystemExceptions.CouponSystemException;

import Beans.Description;
import Beans.Income;
import Client.ClientFacade;
import Client.CustomerFacade;
import CouponManagmentSystemWS.CommonServices;
import CouponManagmentSystemWS.BuissnesDelegate.BuissnesDelegate;
@Consumes(MediaType.APPLICATION_JSON)

@Produces(MediaType.APPLICATION_JSON)

@Path("/CustomerService")
public class CustomerService {
	private BuissnesDelegate buissnes=new BuissnesDelegate();
	
	@Context
	HttpServletRequest req;
CommonServices commoneService=new CommonServices();
	private CustomerFacade getCustomerFacade() throws CouponSystemException {
		if (req.getSession().getAttribute("clientFacade")!=null) {
		return (CustomerFacade) req.getSession().getAttribute("clientFacade");}
		else
			throw new CouponSystemException("No Session!");
	}

	//Works
	@Path("getCustomerDetails")
	@GET
	public Response getCustomer() throws CouponSystemException {
		try {
			System.out.println(getCustomerFacade().getCustomerDetails());
			return Response.status(Response.Status.OK).header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
					.allow("OPTIONS").entity(getCustomerFacade().getCustomerDetails()).build();
		} catch (Exception e) {
			e.printStackTrace();
			req.getSession().invalidate();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
					.allow("OPTIONS").entity(e).build();
		}

	}

	//Works
	@Path("getCustomerCoupons")
	@GET
	public Response getCustomerCoupons() throws CouponSystemException {
		try {
			return Response.status(Response.Status.OK).header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
					.allow("OPTIONS").entity(getCustomerFacade().getCustomerCoupon()).build();
		} catch (Exception e) {
			e.printStackTrace();
			req.getSession().invalidate();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
					.allow("OPTIONS").entity(e).build();

		}
	}
	
		@Path("getAllCoupons")
		@GET
		public Response getAllCoupons() throws CouponSystemException {
			try {
				System.out.println("from get all coupons customer"+ getCustomerFacade().getAllCoupons());
				return Response.status(Response.Status.OK).header("Access-Control-Allow-Origin", "*")
						.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
						.allow("OPTIONS").entity(getCustomerFacade().getAllCoupons()).build();
			} catch (Exception e) {
				e.printStackTrace();
				req.getSession().invalidate();
				return Response.status(Response.Status.INTERNAL_SERVER_ERROR).header("Access-Control-Allow-Origin", "*")
						.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
						.allow("OPTIONS").entity(e).build();

			}
		}
	
	@Path("getCustomerCouponsBycategory")
	@GET
	public Response getCustomerCouponsByCategory(@QueryParam("categoryId") int categoryId) throws CouponSystemException {
		try {
			List<String> dBcategory= (List<String>) commoneService.getCategories();
//			int i=0;
//			System.out.println("category is "+category);
//			System.out.println("categories from DB:="+dBcategory);
//		for (Iterator iterator = dBcategory.iterator(); iterator.hasNext();) {
//			String string = (String) iterator.next();
//			i++;
//			if (string.equals(category)) {
//				break;
//			}
//		}
//			for (String string : dBcategory) {
//				i++;
//	
//				if (string.equals(category)) {
//					categoryId=i;
//					break;
//				}
//			}
			return Response.status(Response.Status.OK).header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
					.allow("OPTIONS").entity(getCustomerFacade().getCustomerCouponsByCategory(categoryId)).build();
		} catch (Exception e) {
			e.printStackTrace();
			req.getSession().invalidate();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
					.allow("OPTIONS").entity(e).build();
		}

	}
	@Path("getAllCouponsBycategory")
	@GET
	public Response getAllCouponsByCategory(@QueryParam("categoryId") int categoryId) throws CouponSystemException {
		try {
			return Response.status(Response.Status.OK).header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
					.allow("OPTIONS").entity(getCustomerFacade().getCouponDAO().getAllCouponsByCategoryId(categoryId)).build();
		} catch (Exception e) {
			e.printStackTrace();
			req.getSession().invalidate();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
					.allow("OPTIONS").entity(e).build();
		}

	}
	@Path("getCustomerCouponsByMaxPrice")
	@GET
	public Response getCustomerCouponsByMaxPrice(@QueryParam("maxPrice")double maxPrice) throws CouponSystemException {
		try {
			return Response.status(Response.Status.OK).header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
					.allow("OPTIONS").entity(getCustomerFacade().getCustomerCoupons(maxPrice)).build();
		} catch (Exception e) {
			e.printStackTrace();
			req.getSession().invalidate();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
					.allow("OPTIONS").entity(e).build();
		}
	}
	
	//Works
	@Path("purchaseCoupon")
	@POST
	public Response purchaseCoupo(Coupon coupon) throws CouponSystemException {
		try {
			
			System.out.println("from Purchase coupon customer service: ");
			getCustomerFacade().purchase(coupon);
			System.out.println("from Purchase Coupon "+coupon.getTitle() +" "+new Date()+" "+ Description.CUSTOMER_PURCHASE +" "+coupon.getAmount());
//			Income income=new Income(coupon.getTitle(), new Date(),Description.CUSTOMER_PURCHASE ,(int) coupon.getPrice());
			Income income=new Income(coupon.getTitle(), new Date(), Description.CUSTOMER_PURCHASE,(int) coupon.getPrice(),getCustomerFacade().getCustomerDetails().getId());
			buissnes.storeIncome(income);
			return Response.status(Response.Status.OK).header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
					.allow("OPTIONS").build();
		} catch (Exception e) {
			e.printStackTrace();
			req.getSession().invalidate();
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
			req.getSession().invalidate();
			return Response.status(Response.Status.OK).header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
					.allow("OPTIONS").build();
		} catch (Exception e) {
			e.printStackTrace();
			req.getSession().invalidate();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
					.allow("OPTIONS").entity(e).build();
		}
	}

	@Path("getIncomes")
	@GET
	public Response getIncomes() {
		try {
			System.out.println("from get incomes customer service ");
			return Response.status(Response.Status.OK).header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
					.allow("OPTIONS").entity(buissnes.getAllIncomeByCustomer(getCustomerFacade().getCustomerDetails().getId())).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
					.allow("OPTIONS").entity(e).build();
		}
	}
}
