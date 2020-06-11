package CouponManagmentSystemWS;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ApplicationPath;

import com.couponManagmentSystem.couponSystemExceptions.CouponSystemException;

import Client.AdminFacade;
import Client.ClientFacade;
import Client.ClientType;
import Client.CompanyFacade;
import Client.CustomerFacade;
import Client.LoginManager;



@Path("/LoginFilter")
public class LoginFilter <HTTPServletRequest, HttpSession> {

	@Context
	HttpServletRequest req;
	HttpSession session;
	
	@GET
	@Path("/login")
	@Produces(MediaType.APPLICATION_JSON)
	public Response login(@QueryParam("email") String email, @QueryParam("password") String password,
			@QueryParam("clientType") String clientType) {
		System.out.println(email+" "+password + " "+clientType );
		if(req.getSession(false) != null)
		{
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
		else {
		try {
			System.out.println(email+" "+password + " "+clientType );
			ClientFacade client = null;
			switch (clientType) {
			case "company":

				client = (CompanyFacade) LoginManager.getInstance().login(email, password, ClientType.COMPANY);
				System.out.println("company case");
				break;

			case "customer":
				client = (CustomerFacade) LoginManager.getInstance().login(email, password, ClientType.CUSTOMER);
				System.out.println("customer case");
				break;
			case "admin":
				client = (AdminFacade) LoginManager.getInstance().login(email, password, ClientType.ADMINISTRATOR);
				System.out.println("admin case");
				break;
			default:
				return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();

			}
			
				req.getSession(true).setAttribute("clientFacade", client);
		
			  HttpSession session=(HttpSession) req.getSession();
			System.out.println("session id="+ ((javax.servlet.http.HttpSession) session).getId() );

			return Response.status(Response.Status.OK).build();

		} catch (CouponSystemException e) {

			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
		}
		
		
	}

	
}
