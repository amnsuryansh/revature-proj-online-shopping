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

import com.shopping.dao.EmployeeDAO;
import com.shopping.model.Customer;
import com.shopping.model.Employee;
import com.shopping.model.Product;

public class EmployeeDAOImpl implements EmployeeDAO {
	private static Logger log = Logger.getLogger( EmployeeDAOImpl.class);
	
		@Override
		public List<Product> addProduct(String productname, double price, int quantity, String category)
				throws BusinessException {
			List<Product> productList = new ArrayList<>();
			try(Connection connection = MySqlDbConnection.getConnection()){
				String sql = "insert into product (product_name,product_price,product_quantity,product_category) values (?,?,?,?)";
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1, productname);
				preparedStatement.setDouble(2,price);
				preparedStatement.setInt(3, quantity);
				preparedStatement.setString(4, category);
				
				int resultSet = preparedStatement.executeUpdate();
				if(resultSet ==1) {
					Product product =new Product();
					product.setProduct_name(productname);
					product.setProduct_price(price);
					product.setQuantity_present(quantity);
					product.setProduct_category(category);
					
					productList.add(product);
				}
			}catch (ClassNotFoundException | SQLException e) {
				log.error(e);
				throw new BusinessException("Error:10 Internal error occured contact sysadmin");
			}
			return productList;
		}
		
		@Override
		public Employee isValidEmployee(String employee_email, String employee_password) throws BusinessException {
			Employee employee = new Employee();
			
			try(Connection connection = MySqlDbConnection.getConnection()){
				String sql = "select employee_mail,employee_password from employee where employee_mail = ? and employee_password = ?";
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1,employee_email);
				preparedStatement.setString(2,employee_password);
				
				ResultSet resultSet = preparedStatement.executeQuery();
				if(resultSet.next()) {
					employee.setEmployee_mail(employee_email);
					employee.setEmployee_password(resultSet.getString("employee_password"));
					
				}
				} catch(ClassNotFoundException | SQLException e) {
				log.error(e);
				throw new BusinessException("Error:30 Internal error occured contact sysadmin");
			}
			
			
			return employee;
		}
		@Override
		public Customer getCustomerById(int id) throws BusinessException {
			Customer customer = new Customer();
				try(Connection connection = MySqlDbConnection.getConnection()){
					String sql ="select first_name,last_name,customer_mail,customer_password from customer where customer_id=?";
					PreparedStatement preparedStatement = connection.prepareStatement(sql);
					preparedStatement.setInt(1,id);
					
					ResultSet resultSet = preparedStatement.executeQuery();
					while(resultSet.next()) {
						customer.setCustomer_id(id);
						customer.setFirst_name(resultSet.getString("first_name"));
						customer.setLast_name(resultSet.getString("last_name"));
						customer.setCustomer_mail(resultSet.getString("customer_mail"));
						customer.setCustomer_password(resultSet.getString("customer_password"));
					}
			    } catch(ClassNotFoundException | SQLException e) {
				log.error(e);
				throw new BusinessException("Error:30 Internal error occured contact sysadmin");
			}
			return customer;
		}
		
		@Override
		public Customer getCustomerByFirstName(String firstName) throws BusinessException {
				Customer customer = new Customer();
				try(Connection connection = MySqlDbConnection.getConnection()){
					String sql ="select customer_id,first_name,last_name,customer_mail,customer_password from customer where first_name=?";
					PreparedStatement preparedStatement = connection.prepareStatement(sql);
					preparedStatement.setString(1,firstName);
					
					ResultSet resultSet = preparedStatement.executeQuery();
					while(resultSet.next()) {
						customer.setCustomer_id(resultSet.getInt("customer_id"));
						customer.setFirst_name(firstName);
						customer.setLast_name(resultSet.getString("last_name"));
						customer.setCustomer_mail(resultSet.getString("customer_mail"));
						customer.setCustomer_password(resultSet.getString("customer_password"));
						
						
						
					}
			    } catch(ClassNotFoundException | SQLException e) {
				log.error(e);
				throw new BusinessException("Error:30 Internal error occured contact sysadmin");
			}
			return customer;
		}
		
			/*@Override
			public Customer getCustomerByLastName(String lastName) throws BusinessException {
				Customer customer = new Customer();
				try(Connection connection = MySqlDbConnection.getConnection()){
					String sql ="select customer_id,first_name,last_name,customer_mail,customer_password from customer where last_name=?";
					PreparedStatement preparedStatement = connection.prepareStatement(sql);
					preparedStatement.setString(1,lastName);
					
					ResultSet resultSet = preparedStatement.executeQuery();
					while(resultSet.next()) {
						customer.setCustomer_id(resultSet.getInt("id"));
						customer.setFirst_name(resultSet.getString("first_name"));
						customer.setLast_name(lastName);
						customer.setCustomer_mail(resultSet.getString("customer_mail"));
						customer.setCustomer_password(resultSet.getString("customer_password"));
						
						
					}
			    } catch(ClassNotFoundException | SQLException e) {
				log.error(e);
				throw new BusinessException("Error:30 Internal error occured contact sysadmin");
			}
			return customer;
		}*/
			
		@Override
		public Customer getCustomerByEmail(String email) throws BusinessException {
			Customer customer = new Customer();
			try(Connection connection = MySqlDbConnection.getConnection()){
				String sql ="select customer_id,first_name,last_name,customer_mail,customer_password from customer where customer_mail=?";
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1,email);
				
				ResultSet resultSet = preparedStatement.executeQuery();
				while(resultSet.next()) {
					customer.setCustomer_id(resultSet.getInt("customer_id"));
					customer.setFirst_name(resultSet.getString("first_name"));
					customer.setLast_name(resultSet.getString("last_name"));
					customer.setCustomer_mail(email);
					customer.setCustomer_password(resultSet.getString("customer_password"));
					
					
				}
		    } catch(ClassNotFoundException | SQLException e) {
			log.error(e);
			throw new BusinessException("Error:30 Internal error occured contact sysadmin");
		}
		return customer;
		}
		
		@Override
		public Customer getCustomerByOrderId(int orderId) throws BusinessException {
			Customer customer = null;
			try (Connection connection = MySqlDbConnection.getConnection()) {
				String sql = "select customer_id,first_name,last_name,customer_email from order join customer on orderCustomerId = customer_id where order_id=?";
				//String sql = "select customer_id,first_name,last_name,customer_email from customer join order on order.orderCustomerId = customer_id where orderId=?";
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setInt(1, orderId);
		
				ResultSet resultSet = preparedStatement.executeQuery();
				if (resultSet.next()) {
					customer = new Customer();
					customer.setCustomer_id(resultSet.getInt("customer_id"));
					customer.setFirst_name(resultSet.getString("first_name"));
					customer.setLast_name(resultSet.getString("last_name"));
					customer.setCustomer_mail(resultSet.getString("customer_mail"));
					
					/*customer.setCustomerId(resultSet.getInt("cu_id"));
					customer.setCustomerName(resultSet.getString("cu_name"));
					customer.setCustomerUsername(resultSet.getString("cu_username"));
					customer.setCustomerEmail(resultSet.getString("cu_email"));*/
				} else {
					throw new BusinessException("Customer Id is not matched in our Database!");
				}
			} catch (ClassNotFoundException | SQLException e) {
				log.warn(e.getMessage());
				throw new BusinessException("Internal Problem Occured. Contact sysAdmin!");
			}
			return customer;
		}
			
		
}
