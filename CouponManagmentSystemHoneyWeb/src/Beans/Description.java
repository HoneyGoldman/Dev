package Beans;

public enum Description {

		CUSTOMER_PURCHASE("Customer_Purchased_Coupon"),COMPANY_NEW_COUPON("Company_Added_Coupon")
		,COMPANY_UPDATE_COUPON("Company_Edited_Coupon");
		private String description;

			public String getDescription() {
			return description;
		}

			private Description(String description) {
				this.description=description;
			}
		}

