package com.shopping.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.app.exception.BusinessException;
import com.shopping.dao.CustomerDAO;
import com.shopping.dao.impl.CustomerDAOImpl;
import com.shopping.model.Customer;

import com.shopping.service.CustomerService;

public class CustomerServiceImpl implements CustomerService{
	
	private CustomerDAO customerDAO=new CustomerDAOImpl();
	
	
	
	@Override
	public List<Customer> addingConsumer(String first_name,String last_name,String customer_mail,String customer_password) throws BusinessException {
		List<Customer> consumerList = new ArrayList<>();
		consumerList = customerDAO.addingConsumer( first_name, last_name, customer_mail,customer_password);
		
		return consumerList;
		
	}
	
	@Override
	public Customer isValidCustomer(String email, String password) throws BusinessException {
		Customer customer = null;
		if(email!=null) {
				customer = customerDAO.isValidCustomer(email,password);
			
		}
		else {
			throw new BusinessException("Please enter valid email");
		}
		
		return customer;
	}
	
	
}
