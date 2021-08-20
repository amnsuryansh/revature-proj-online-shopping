package com.shopping.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.app.exception.BusinessException;
import com.shopping.dao.OrderDAO;
import com.shopping.dao.impl.OrderDAOImpl;
import com.shopping.model.Cart;
import com.shopping.model.Order;
import com.shopping.service.OrderService;

public class OrderServiceImpl implements OrderService {
	private OrderDAO orderDAO = new OrderDAOImpl();
	
	@Override
	public int placeOrder(Cart cart) throws BusinessException {
		int a=0;
		a =orderDAO.placeOrder(cart);
		
		return a;
	}
	
//	@Override
//	public List<Order> viewOrders() throws BusinessException {
//		List<Order> orderList = new ArrayList<>();
//		orderList = orderDAO.viewOrders();
//		return orderList;
//	}
//	
	@Override
	public int updateOrderStatus(int orderId, String status) throws BusinessException {
		int c = orderDAO.updateOrderStatus(orderId, status);
		return c;
	}
	
	@Override
	public List<Order> getOrders(int customerId) throws BusinessException {
		List<Order> orderList = new ArrayList<>();
		orderList = orderDAO.getOrders(customerId);
		return orderList;
	}

}
