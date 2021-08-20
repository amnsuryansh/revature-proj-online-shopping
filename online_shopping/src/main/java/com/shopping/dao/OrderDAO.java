package com.shopping.dao;

import java.util.List;

import com.app.exception.BusinessException;
import com.shopping.model.Cart;
import com.shopping.model.Order;

public interface OrderDAO {
	
	public int placeOrder(Cart cart) throws BusinessException;
	//public List<Order> viewOrders() throws BusinessException;
	public int updateOrderStatus(int orderId,String status) throws BusinessException;
	public List<Order> getOrders(int customerId) throws BusinessException;
}
