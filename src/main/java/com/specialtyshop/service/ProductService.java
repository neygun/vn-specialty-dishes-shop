package com.specialtyshop.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.specialtyshop.entity.Product;
import com.specialtyshop.entity.Supplier;

public interface ProductService {
		
	public Product findById(Integer productId);
		
	public void save(Product product);
	
	public void deleteById(Integer productId);
	
	public Page<Product> findAllProducts(Double minPrice, Double maxPrice, String sortBy, int page);
	
	public Page<Product> findByMainCategoryId(Integer mainCategoryId, Double minPrice, Double maxPrice, String sortBy, int page);
	
	public Page<Product> findBySubCategoryId(Integer subCategoryId, Double minPrice, Double maxPrice, String sortBy, int page);
	
	public Page<Product> findBestSellingProducts(Double minPrice, Double maxPrice, String sortBy, int page);
	
	public Page<Product> searchProducts(String keyword, Double minPrice, Double maxPrice, String sortBy, int page);
	
	public List<Product> findBySupplier(Supplier supplier, String keyword);
	
	public void setCurrentPurchaseOrder(Integer productId, Integer purchaseOrderId);
}
