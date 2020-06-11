package Beans;

import java.util.Date;


public class Income {

		@Override
	public String toString() {
		return "Income [id=" + id + ", name=" + name + ", date=" + date + ", description=" + description + ", amount="
				+ amount + ", clientId=" + clientId + "]";
	}
		private int id;
		private String name;
		
		private Date date;
		private Description description;
		private int amount;
		int clientId;
		public Income(String name, Date date, Description description, int amount, int clientId) {
			super();
			this.name = name;
			this.date = date;
			this.description = description;
			this.amount = amount;
			this.clientId = clientId;
		}
		public Income(int id, String name, Date date, Description description, int amount, int clientId) {
			super();
			this.id = id;
			this.name = name;
			this.date = date;
			this.description = description;
			this.amount = amount;
			this.clientId = clientId;
		}
		public int getClientId() {
			return clientId;
		}
		public void setClientId(int clientId) {
			this.clientId = clientId;
		}
		public Income(String name, Date date, Description description, int amount) {
			super();
			this.name = name;
			this.date = date;
			this.description = description;
			this.amount = amount;
		}
		public Income() {
			super();
		}
		public Income(int id, String name, Date date, Description description, int amount) {
			super();
			this.id = id;
			this.name = name;
			this.date = date;
			this.description = description;
			this.amount = amount;
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public Date getDate() {
			return date;
		}
		public void setDate(Date date) {
			this.date = date;
		}
		public Description getDescription() {
			return description;
		}
		public void setDescription(Description description) {
			this.description = description;
		}
		public int getAmount() {
			return amount;
		}
		public void setAmount(int amount) {
			this.amount = amount;
		}

	}


