package com.shopping.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.app.exception.BusinessException;
import com.shopping.dao.EmployeeDAO;
import com.shopping.dao.impl.EmployeeDAOImpl;
import com.shopping.model.Customer;
import com.shopping.model.Employee;
import com.shopping.model.Product;
import com.shopping.service.EmployeeService;

public class EmployeeServiceImpl implements EmployeeService {
	
	private EmployeeDAO employeeDAO=new EmployeeDAOImpl();
	
	@Override
	public List<Product> addProduct(String productname, double price, int quantity, String category)
			throws BusinessException{
		List<Product> productList = new ArrayList<>();
		
		
		return productList;
	}
	
	@Override
	public Employee isValidEmployee(String employee_email, String employee_password) throws BusinessException {
		Employee employee = null;
		if(employee_email!=null) {
				employee = employeeDAO.isValidEmployee(employee_email,employee_password);
			
		}
		else {
			throw new BusinessException("Please enter valid email");
		}
		
		return employee;
	}
	
	@Override
	public Customer getCustomerById(int id) throws BusinessException {
		Customer customer =null;
		customer = employeeDAO.getCustomerById(id);
		return customer;
	}
	
	@Override
	public Customer getCustomerByFirstName(String firstName) throws BusinessException {
		Customer customer =null;
		customer = employeeDAO.getCustomerByFirstName(firstName);
		return customer;
		
	}
	
	/*@Override
	public Customer getCustomerByLastName(String lastName) throws BusinessException {
		Customer customer =null;
		customer = employeeDAO.getCustomerByLastName(lastName);
		return customer;
	}*/
	
	@Override
	public Customer getCustomerByEmail(String email) throws BusinessException {
		Customer customer =null;
		customer = employeeDAO.getCustomerByEmail(email);
		return customer;
	}
	
	@Override
	public Customer getCustomerByOrderId(int orderId) throws BusinessException {
		
		return null;
	}
}
