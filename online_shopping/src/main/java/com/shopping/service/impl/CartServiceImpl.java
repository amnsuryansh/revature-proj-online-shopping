package com.shopping.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.app.exception.BusinessException;
import com.shopping.dao.CartDAO;
import com.shopping.dao.impl.CartDAOImpl;
import com.shopping.model.Cart;
import com.shopping.service.CartService;

public class CartServiceImpl implements CartService{
	private CartDAO cartDAO=new CartDAOImpl();
	@Override
	public int addProductToCartById(int id,int customer_id) throws BusinessException {
		int a=0;
		a = cartDAO.addProductToCartById(id,customer_id) ;
		return a;
		
	}
	
	@Override
	public List<Cart> viewCart(Cart cart) throws BusinessException {
		List<Cart> cartList = new ArrayList<>();
		cartList = cartDAO.viewCart(cart);
		return cartList;
	}
	
	@Override
	public int addProductToCartByCategory(String category, int customer_id) throws BusinessException {
		int a=0;
		a = cartDAO.addProductToCartByCategory(category,customer_id) ;
		return a;
	}
	
	@Override
	public int addProductToCartByName(String name, int customer_id) throws BusinessException {
		int a=0;
		a = cartDAO.addProductToCartByName(name,customer_id) ;
		return a;
	}

}
