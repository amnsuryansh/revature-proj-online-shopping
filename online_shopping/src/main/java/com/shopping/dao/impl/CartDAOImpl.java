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
import com.shopping.dao.CartDAO;
import com.shopping.model.Cart;
import com.shopping.model.Customer;
import com.shopping.model.Product;

public class CartDAOImpl implements CartDAO {
	
	private static Logger log = Logger.getLogger(CartDAOImpl.class);
	
	@Override
	public int addProductToCartById(int id,int customer_id) throws BusinessException {
		
		int c;
		try (Connection connection = MySqlDbConnection.getConnection()) {
			String sql = "insert into cart(cart_customer_id,cart_product_id) values (?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, customer_id);
			preparedStatement.setInt(2, id);
			
			c = preparedStatement.executeUpdate();
//			preparedStatement.setInt(3, cart.getQuantity());
//			if(c==1) {
//				Cart cart = new Cart();
//				cart.setProduct_id(id);
//				cart.setCustomer_id(customer_id);
//				cart.setQuantity(quantity);
//			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException(e.getMessage() + " Internal Problem Occured. Contact sysAdmin!");
		}
		return c;
	}
	
	@Override
	public List<Cart> viewCart(Cart cart) throws BusinessException {
//		Cart cart1 = new Cart();
		List<Cart> cartList = new ArrayList<>();
		try(Connection connection = MySqlDbConnection.getConnection()){
			String sql = "select cart_id,customer_id,customer_mail,product_id,product_name,product_price,product_category from cart"
					+ " join product on cart_product_id = product_id"
					+ " join customer on cart_customer_id = customer_id where customer_id=?";
			
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setInt(1, CustomerDAOImpl.ad);
			ResultSet resultSet =preparedStatement.executeQuery();
			while(resultSet.next()) {
				Customer customer = new Customer();
				customer.setCustomer_id(resultSet.getInt("customer_id"));
				customer.setCustomer_mail(resultSet.getString("customer_mail"));
				
				Product product = new Product();
				product.setProduct_id(resultSet.getInt("product_id"));
				product.setProduct_name(resultSet.getString("product_name"));
				product.setProduct_price(resultSet.getDouble("product_price"));
				product.setProduct_category(resultSet.getString("product_category"));
				Cart againCart = new Cart();
				againCart.setCart_id(resultSet.getInt("cart_id"));
				againCart.setCustomer_id(resultSet.getInt("customer_id"));
				againCart.setProduct(product);
				cartList.add(againCart);
				
			}
		}
		catch(ClassNotFoundException | SQLException e) {
			log.error(e);
			throw new BusinessException("Error:20 Internal error occured contact sysadmin");
		}
		
		return cartList;
	}
	
	@Override
	public int addProductToCartByCategory(String category, int customer_id) throws BusinessException {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	@Override
	public int addProductToCartByName(String name, int customer_id) throws BusinessException {
		// TODO Auto-generated method stub
		return 0;
	}
}
