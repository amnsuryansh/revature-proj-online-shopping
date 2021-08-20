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
import com.shopping.dao.ProductMethodDAO;
import com.shopping.model.Product;

public class ProductMethodDAOImpl implements ProductMethodDAO{
	
	private static Logger log = Logger.getLogger( ProductMethodDAOImpl.class);
	
	@Override
	public List<Product> getAllProducts() throws BusinessException{
		List<Product> productList = new ArrayList<>();
		try(Connection connection=MySqlDbConnection.getConnection()){
			String sql = "select product_id,product_name,product_price,quantity_present from product";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				Product product = new Product();
				product.setProduct_id(resultSet.getInt("product_id"));
				product.setProduct_name(resultSet.getString("product_name"));
				product.setProduct_price(resultSet.getDouble("product_price"));
				product.setQuantity_present(resultSet.getInt("quantity_present"));
				productList.add(product);
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			log.error(e);
			throw new BusinessException("Error:10 Internal error occured contact sysadmin");
		}	
		return productList;
	}
	
	@Override
	public Product getProductById(int id) throws BusinessException {
		Product product = null;
		try(Connection connection =MySqlDbConnection.getConnection()){
			String sql = "select product_name,product_price from product where product_id = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				product = new Product();
				product.setProduct_id(id);
				product.setProduct_name(resultSet.getString("product_name"));
				product.setProduct_price(resultSet.getDouble("product_price"));
				
			}else {
				throw new BusinessException("Entered product id "+id+" doesnt exist");
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			log.error(e);
			throw new BusinessException("Internal error occured contact sysadmin");
		}
		
		return product;
	}
	
	@Override
	public Product getProductByName(String name) throws BusinessException {
		Product product = null;
		try(Connection connection =MySqlDbConnection.getConnection()){
			String sql = "select product_id,product_name,product_price from product where product_name = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, name);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				product = new Product();
				product.setProduct_name(name);
				product.setProduct_id(resultSet.getInt("product_id"));
				product.setProduct_price(resultSet.getDouble("product_price"));
			}else {
				throw new BusinessException("Entered product name "+name+" doesnt exist");
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			log.error(e);
			throw new BusinessException("Internal error occured contact sysadmin");
		}
		
		return product;
	}
	
	@Override
	public List<Product> getProductByCategory(String category) throws BusinessException {
		List<Product> productList =new ArrayList<>();
		try(Connection connection =MySqlDbConnection.getConnection()){
			String sql = "select product_id,product_name,product_price from product where product_category = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, category);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				Product product = new Product();
				product = new Product();
				product.setProduct_category(category);
				product.setProduct_id(resultSet.getInt("product_id"));
				product.setProduct_name(resultSet.getString("product_name"));
				
				product.setProduct_price(resultSet.getDouble("product_price"));
				productList.add(product);
			}if(productList.size()==0) {
				throw new BusinessException("No Products for category "+ category);
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			log.error(e);
			throw new BusinessException("Internal error occured contact sysadmin");
		}
		
		return productList;
	}
}
