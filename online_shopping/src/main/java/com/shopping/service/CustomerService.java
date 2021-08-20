package com.shopping.service;

import java.util.List;

import com.app.exception.BusinessException;
import com.shopping.model.Customer;



public interface CustomerService {
	
	
		public List<Customer> addingConsumer(String first_name,String last_name,String customer_mail,String customer_password) throws BusinessException;
		public Customer isValidCustomer(String email,String password) throws BusinessException;
		

}
