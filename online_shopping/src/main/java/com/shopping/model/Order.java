package com.shopping.model;

public class Order {
	
	private int orderId;
	private int customerId;
	private int productId;
	private String orderStatus;
	private Product product;
	private Customer customer;
	
	public Order() {
		
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Order(int orderId, int customerId, int productId, String orderStatus, Product product, Customer customer) {
		super();
		this.orderId = orderId;
		this.customerId = customerId;
		this.productId = productId;
		this.orderStatus = orderStatus;
		this.product = product;
		this.customer = customer;
	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", customerId=" + customerId + ", productId=" + productId
				+ ", orderStatus=" + orderStatus + ", product=" + product + ", customer=" + customer + "]";
	}
	
	
	
	/*CREATE TABLE `revature_project_one`.`order` (
		  `orderId` INT NOT NULL,
		  `orderCustomerId` INT NOT NULL,
		  `orderProductId` INT NOT NULL,
		  `orderStatus` VARCHAR(45) NOT NULL DEFAULT 'Ordered',
		  PRIMARY KEY (`orderId`));
		  
		  ALTER TABLE `revature_project_one`.`order` 
		ADD INDEX `fk_product_idx` (`orderProductId` ASC) VISIBLE,
		ADD INDEX `fk_customer_idx` (`orderCustomerId` ASC) VISIBLE;
		;
		ALTER TABLE `revature_project_one`.`order` 
		ADD CONSTRAINT `fk_product`
		  FOREIGN KEY (`orderProductId`)
		  REFERENCES `revature_project_one`.`product` (`product_id`)
		  ON DELETE NO ACTION
		  ON UPDATE NO ACTION,
		ADD CONSTRAINT `fk_customer`
		  FOREIGN KEY (`orderCustomerId`)
		  REFERENCES `revature_project_one`.`customer` (`customer_id`)
		  ON DELETE NO ACTION
		  ON UPDATE NO ACTION;
	 
	 */
	
	
	

}
