package com.shopping.dao;

import java.util.List;

import com.app.exception.BusinessException;
import com.shopping.model.Product;

public interface ProductMethodDAO {
	
	public List<Product> getAllProducts() throws BusinessException;
	public Product getProductById(int id) throws BusinessException;
	public Product getProductByName(String name) throws BusinessException;
	public List<Product> getProductByCategory(String category) throws BusinessException;
}
