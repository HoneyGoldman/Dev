package CouponManagmentSystemWS;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.couponManagmentSystem.Beans.Category;
import com.couponManagmentSystem.Beans.Coupon;

import Beans.Description;
import Beans.Income;
import Client.CompanyFacade;
import CouponManagmentSystemWS.BuissnesDelegate.BuissnesDelegate;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/CompanyService")
public class CompanyService {
	
	private BuissnesDelegate buissnes=new BuissnesDelegate();
	@Context
	HttpServletRequest req;

	private CompanyFacade getCompanyFacade() {
		return (CompanyFacade) req.getSession().getAttribute("clientFacade");
	}

	//Works
	@Path("addCoupon")
	@POST
	public Response addCoupon(Coupon coupon) {
		try {
			getCompanyFacade().addCoupon(coupon);
			Income income=new Income(coupon.getTitle(), new Date(),Description.COMPANY_NEW_COUPON,100,getCompanyFacade().getCompanyDetails().getId());		
			buissnes.storeIncome(income);
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
	@Path("updateCoupon")
	@PUT
	public Response updateCoupon(Coupon coupon) {
		try {
			getCompanyFacade().updateCoupon(coupon);
			Income income=new Income(coupon.getTitle(), new Date(),Description.COMPANY_UPDATE_COUPON,100,getCompanyFacade().getCompanyDetails().getId());		
			buissnes.storeIncome(income);
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
	@Path("deleteCoupon")
	@DELETE
	public Response deleteCoupon(@QueryParam("couponId")int couponId) {
		try {
			getCompanyFacade().deleteCoupon(couponId);
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
	

	
		@Path("getCouponById")
		@GET
		public Response getCouponById(@QueryParam("id")int id) {
			try {
				System.out.println("from comany service get coupon by id"+getCompanyFacade().getCoupon(id));
				return Response.status(Response.Status.OK).header("Access-Control-Allow-Origin", "*")
						.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
						.allow("OPTIONS").entity(getCompanyFacade().getCoupon(id)).build();
			} catch (Exception e) {
				e.printStackTrace();
				return Response.status(Response.Status.INTERNAL_SERVER_ERROR).header("Access-Control-Allow-Origin", "*")
						.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
						.allow("OPTIONS").entity(e).build();
			}
		}
		
		
	@Path("getCompanyCouponByTitle")
	@GET
	public Response getCompanyCoupons(@QueryParam("title") String text) {
		try {
//			System.out.println("get coupons "+getCompanyFacade().getCompanyCoupons()+" company coupons from dao"+getCompanyFacade().getCompanyCoupons());
			return Response.status(Response.Status.OK).header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
					.allow("OPTIONS").entity(getCompanyFacade().getCompanyCouponByTitle(text)).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
					.allow("OPTIONS").entity(e).build();
		}
	}

	//Works
	@Path("getCompanyCouponsByMaxPrice")
	@GET
	public Response getCompanyCoupons(@QueryParam("maxPrice") double maxPrice) {
		try {
			return Response.status(Response.Status.OK).header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
					.allow("OPTIONS").entity(getCompanyFacade().getCompanyCoupons(maxPrice)).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
					.allow("OPTIONS").entity(e).build();
		}
	}
	
	//Works
	@Path("getCompanyCouponsByCategory")
	@GET
	public Response getCompanyCoupons(@QueryParam("category")Category category) {
		try {
			return Response.status(Response.Status.OK).header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
					.allow("OPTIONS").entity(getCompanyFacade().getCompanyCoupons(category)).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
					.allow("OPTIONS").entity(e).build();
		}
	}

		

	//Works
	@Path("getCompanyDetails")
	@GET
	public Response getCompanyDetails() {
		try {
			return Response.status(Response.Status.OK).header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
					.allow("OPTIONS").entity(getCompanyFacade().getCompanyDetails()).build();
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
			req.getSession().invalidate();
			System.out.println("from log out company");
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
	
	//works
			@Path("getAllCompanyCoupons")
			@GET
			public Response getAllCompanyCoupons() {
				try {
					System.out.println("from get all coupons company service "+getCompanyFacade().getCompanyCoupons());
					return Response.status(Response.Status.OK).header("Access-Control-Allow-Origin", "*")
							.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
							.allow("OPTIONS").entity(getCompanyFacade().getCompanyCoupons()).build();
				} catch (Exception e) {
					e.printStackTrace();
					return Response.status(Response.Status.INTERNAL_SERVER_ERROR).header("Access-Control-Allow-Origin", "*")
							.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
							.allow("OPTIONS").entity(e).build();
				}
			}
			
			//Works
			@Path("getIncomes")
			@GET
			public Response getIncomes() {
				try {
					System.out.println("from get incomes company service ");
					return Response.status(Response.Status.OK).header("Access-Control-Allow-Origin", "*")
							.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
							.allow("OPTIONS").entity(buissnes.getAllIncomeByCompany(getCompanyFacade().getCompanyDetails().getId())).build();
				} catch (Exception e) {
					e.printStackTrace();
					return Response.status(Response.Status.INTERNAL_SERVER_ERROR).header("Access-Control-Allow-Origin", "*")
							.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
							.allow("OPTIONS").entity(e).build();
				}
			}
			
}
