package CouponManagmentSystemWS;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import Client.CommonFacade;



	@Path("/CommonService")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public class CommonServices {
		@Context
		HttpServletRequest req;
		public CommonFacade commonFacade=new CommonFacade();
		public CommonFacade getCommonFacade() {
			return commonFacade;
		}
		@Path("getCategories")
		@GET
		public Response getCategories() {
			try {
				getCommonFacade();
				System.out.println("from get Categories"+CommonFacade.getCategories());
				getCommonFacade();
				return Response.status(Response.Status.OK).header("Access-Control-Allow-Origin", "*")
						.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
						.allow("OPTIONS").entity(CommonFacade.getCategories()).build();
			} catch (Exception e) {
				e.printStackTrace();
				return Response.status(Response.Status.INTERNAL_SERVER_ERROR).header("Access-Control-Allow-Origin", "*")
						.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
						.allow("OPTIONS").entity(e).build();
			}	
			}
		
		@Path("categoryGetter")
		@GET
		public Response getcategory(@QueryParam("categoryId")int categoryId) {
			try {
				System.out.println("from get category "+CommonFacade.getCategory(categoryId));
				return Response.status(Response.Status.OK).header("Access-Control-Allow-Origin", "*")
						.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
						.allow("OPTIONS").entity(CommonFacade.getCategory(categoryId)).build();
			} catch (Exception e) {
				e.printStackTrace();
				return Response.status(Response.Status.INTERNAL_SERVER_ERROR).header("Access-Control-Allow-Origin", "*")
						.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
						.allow("OPTIONS").entity(e).build();
			}
		}
		
}
