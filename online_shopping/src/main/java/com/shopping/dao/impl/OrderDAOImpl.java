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
import com.shopping.dao.OrderDAO;
import com.shopping.model.Cart;
import com.shopping.model.Order;
import com.shopping.model.Product;

public class OrderDAOImpl implements OrderDAO {
	private static Logger log = Logger.getLogger(OrderDAOImpl.class);
	@Override
	public int placeOrder(Cart cart) throws BusinessException {
		int c;
		try (Connection connection = MySqlDbConnection.getConnection()) {
			String sql = "insert into revature_project_one.order(orderCustomerId,orderProductId) select cart_customer_id,cart_product_id from cart where cart_customer_id=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, CustomerDAOImpl.ad);
			preparedStatement.executeUpdate();
			String sql1 = "delete from cart where cart_customer_id=?";
			PreparedStatement preparedStatement1 = connection.prepareStatement(sql1);
			preparedStatement1.setInt(1, cart.getCustomer_id());
			c=preparedStatement1.executeUpdate();
			
		}catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException(e.getMessage() + " Error:20 Internal error occured contact sysadmin");
		}
		return c;
	}
	
	
	
	@Override
	public int updateOrderStatus(int orderId,String status) throws BusinessException {
		int c = 0;
		try (Connection connection = MySqlDbConnection.getConnection()) {

			String sql = "update revature_project_one.order set orderStatus = ? where orderId =?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, status);
			preparedStatement.setInt(2, orderId);
			
			c = preparedStatement.executeUpdate();

		} catch(ClassNotFoundException | SQLException e) {
			log.error(e);
			throw new BusinessException(e.getMessage() +"Error:20 Internal error occured contact sysadmin");
		}
		return c;
	}
	
	@Override
	public List<Order> getOrders(int customerId) throws BusinessException {
		List<Order> orderList = new ArrayList<>();
		
		try (Connection connection = MySqlDbConnection.getConnection()) {
			String sql = "select orderId,product_id,product_name,product_price,quantity_present,orderStatus from revature_project_one.order  join product on orderProductId=product_id join customer on orderCustomerId= customer_id where customer_id=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, customerId);
			ResultSet resultSet = preparedStatement.executeQuery();			
			while(resultSet.next()) {
				Order order = new Order();
				order.setOrderId(resultSet.getInt("orderId"));
				order.setProductId(resultSet.getInt("product_id"));
				//order.setProduct_name(resultSet.getString("product_name"));
				//.setPrice(resultSet.getDouble("pro_price"));
				order.setOrderStatus(resultSet.getString("orderStatus"));
				Product product =new Product();
				product.setProduct_name(resultSet.getString("product_name"));
				product.setProduct_price(resultSet.getDouble("product_price"));
				product.setQuantity_present(resultSet.getInt("quantity_present"));
				order.setProduct(product);
				orderList.add(order);
			}
			 
			if(orderList.size()==0) {
				throw new BusinessException("You have no orders");
			}
		} catch(ClassNotFoundException | SQLException e) {
			log.error(e);
			throw new BusinessException(e.getMessage() +"Error:20 Internal error occured contact sysadmin");
		}
		return orderList;
	}

}
