package com.shopping.service;

import java.util.List;

import com.app.exception.BusinessException;
import com.shopping.model.Customer;
import com.shopping.model.Employee;
import com.shopping.model.Product;

public interface EmployeeService {
	 
	public List<Product> addProduct(String productname, double price, int quantity, String category)
			throws BusinessException;
	public Employee isValidEmployee(String employee_email, String employee_password) throws BusinessException;
	public Customer getCustomerById(int id) throws BusinessException;
	public Customer getCustomerByFirstName(String firstName) throws BusinessException;
	//public Customer getCustomerByLastName(String lastName) throws BusinessException;
	public Customer getCustomerByEmail(String email) throws BusinessException;
	public Customer getCustomerByOrderId(int orderId) throws BusinessException;
	
}
