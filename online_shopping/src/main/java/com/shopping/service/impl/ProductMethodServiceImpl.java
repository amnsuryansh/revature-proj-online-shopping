package com.shopping.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.app.exception.BusinessException;
import com.shopping.dao.ProductMethodDAO;
import com.shopping.dao.impl.ProductMethodDAOImpl;
import com.shopping.model.Product;
import com.shopping.service.ProductMethodService;

public class ProductMethodServiceImpl implements ProductMethodService{

	
private ProductMethodDAO productDAO=new ProductMethodDAOImpl();
	
	@Override
	public List<Product> getAllProducts() throws BusinessException {
		List<Product> productList = new ArrayList<>();
		productList = productDAO.getAllProducts();
		
		return productList;
	}
	
	@Override
		public Product getProductById(int id) throws BusinessException {
			Product product=null;
			if(id<100 ||id>1000) {
				throw new BusinessException("Invalid Player Id "+id);
			}else {
				//code here to DAO
				product=productDAO.getProductById(id) ;
				
			}
			
			return product;
		}
	
	@Override
		public Product getProductByName(String name) throws BusinessException {
		Product product=null;
		product = productDAO.getProductByName(name);
			return product;
		}
	
	@Override
		public List<Product> getProductByCategory(String category) throws BusinessException {
		List<Product> productList = new ArrayList<>();
		productList = productDAO.getProductByCategory(category);
		
		return productList;
		
		}
}
