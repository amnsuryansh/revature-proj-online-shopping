package com.shopping.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.app.dao.dbutil.MySqlDbConnection;
import com.app.exception.BusinessException;
import com.shopping.dao.CustomerDAO;
import com.shopping.model.Customer;



public class CustomerDAOImpl implements CustomerDAO {
	
	private static Logger log = Logger.getLogger(CustomerDAOImpl.class);
	
	public static int ad;
	
	@Override
	public List<Customer> addingConsumer(String first_name, String last_name, String customer_mail,
			String customer_password) throws BusinessException {
		List<Customer> customerList = new ArrayList<>();
		try(Connection connection = MySqlDbConnection.getConnection()){
			String sql = "insert into customer (first_name,last_name,customer_mail,customer_password) values (?,?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, first_name);
			preparedStatement.setString(2, last_name);
			preparedStatement.setString(3, customer_mail);
			preparedStatement.setString(4, customer_password);
			
			
			int resultSet = preparedStatement.executeUpdate();
			if(resultSet==1) {
				Customer customer = new Customer();
				
				customer.setFirst_name(first_name);
				customer.setLast_name(last_name);
				customer.setCustomer_mail(customer_mail);
				customer.setCustomer_password(customer_password);
				
				customerList.add(customer);
			}
			
		} catch(ClassNotFoundException | SQLException e) {
			log.error(e);
			throw new BusinessException("Error:20 Internal error occured contact sysadmin");
		}
		return customerList;
	}
	
	@Override
	public Customer isValidCustomer(String email, String password) throws BusinessException {
		Customer customer = new Customer();
		try(Connection connection = MySqlDbConnection.getConnection()){
			String sql = "select customer_id,first_name,last_name,customer_mail,customer_password from customer where customer_mail = ? and customer_password =?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1,email);
			preparedStatement.setString(2,password);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				customer.setCustomer_id(resultSet.getInt("customer_id"));
				customer.setFirst_name(resultSet.getString("first_name"));
				customer.setLast_name(resultSet.getString("last_name"));
				customer.setCustomer_mail(resultSet.getString("customer_mail"));
				customer.setCustomer_password(resultSet.getString("customer_password"));
				ad = resultSet.getInt("customer_id");
				log.info(ad);
				
				
			}
			} catch(ClassNotFoundException | SQLException e) {
			log.error(e);
			throw new BusinessException("Error:30 Internal error occured contact sysadmin");
		}
		return customer;
	}
	
	
	
	

}
