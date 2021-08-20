package com.shopping.dao;

import java.util.List;

import com.app.exception.BusinessException;
import com.shopping.model.Cart;

public interface CartDAO {
	
	//public int addToCartById(int id,int customer_id,int quantity) throws BusinessException;
	public int addProductToCartById(int id,int customer_id) throws BusinessException;
	public int addProductToCartByName(String name,int customer_id) throws BusinessException;
	public int addProductToCartByCategory(String category,int customer_id) throws BusinessException;
	
	public List<Cart> viewCart(Cart cart) throws BusinessException;
}
