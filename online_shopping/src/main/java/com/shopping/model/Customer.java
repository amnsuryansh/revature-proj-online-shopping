package com.shopping.model;

public class Customer {
	
	private int customer_id;
	private String first_name;
	private String last_name;
	private String customer_mail;
	private String customer_password;
	
	public Customer() {
		
	}

	public int getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getCustomer_mail() {
		return customer_mail;
	}

	public void setCustomer_mail(String customer_mail) {
		this.customer_mail = customer_mail;
	}

	public String getCustomer_password() {
		return customer_password;
	}

	public void setCustomer_password(String customer_password) {
		this.customer_password = customer_password;
	}

	public Customer(int customer_id, String first_name, String last_name, String customer_mail,
			String customer_password) {
		super();
		this.customer_id = customer_id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.customer_mail = customer_mail;
		this.customer_password = customer_password;
	}

	@Override
	public String toString() {
		return "Customer [customer_id=" + customer_id + ", first_name=" + first_name + ", last_name=" + last_name
				+ ", customer_mail=" + customer_mail + ", customer_password=" + customer_password + "]";
	}

	
	
	
	
	

}
