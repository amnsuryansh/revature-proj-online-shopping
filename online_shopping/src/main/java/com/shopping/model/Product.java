package com.shopping.model;

public class Product {
	
	private int product_id;
	private String product_name;
	private double product_price;
	private int quantity_present;
	private String product_category;
	
	public Product() {
		
		
	}

	@Override
	public String toString() {
		return "Product [product_id=" + product_id + ", product_name=" + product_name + ", product_price="
				+ product_price + ", quantity_present=" + quantity_present + ", product_category=" + product_category
				+ "]";
	}

	public Product(int product_id, String product_name, double product_price, int quantity_present,
			String product_category) {
		super();
		this.product_id = product_id;
		this.product_name = product_name;
		this.product_price = product_price;
		this.quantity_present = quantity_present;
		this.product_category = product_category;
	}

	public int getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public double getProduct_price() {
		return product_price;
	}

	public void setProduct_price(double product_price) {
		this.product_price = product_price;
	}

	public int getQuantity_present() {
		return quantity_present;
	}

	public void setQuantity_present(int quantity_present) {
		this.quantity_present = quantity_present;
	}

	public String getProduct_category() {
		return product_category;
	}

	public void setProduct_category(String product_category) {
		this.product_category = product_category;
	}
	
	
}
