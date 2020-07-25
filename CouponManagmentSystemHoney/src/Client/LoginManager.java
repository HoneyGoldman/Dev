package Client;

import com.couponManagmentSystem.couponSystemExceptions.CouponSystemException;

public class LoginManager {

	public LoginManager() {
		super();
	};

	private static LoginManager instance;

	public static LoginManager getInstance() {
		if (instance == null) {
			LoginManager.instance = new LoginManager();
			return instance;

		}
		return instance;
	}

	public ClientFacade login(String email, String password, ClientType clientType) throws CouponSystemException {
		switch (clientType) {
		case ADMINISTRATOR:
			AdminFacade admin = new AdminFacade();
			if (admin.login("admin@admin", "admin")) {
				return admin;
			}
			break;

		case COMPANY:
			CompanyFacade company = new CompanyFacade();
			if (company.login(email, password)) {
				return company;
			}
			break;
		case CUSTOMER:
			CustomerFacade customer = new CustomerFacade();
			if (customer.login(email, password)) {
				return customer;

			}

		}
		return null;
	}
}
