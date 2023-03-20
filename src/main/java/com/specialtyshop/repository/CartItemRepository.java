package com.specialtyshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.specialtyshop.entity.CartItem;
import com.specialtyshop.entity.Customer;
import com.specialtyshop.entity.Product;

public interface CartItemRepository extends JpaRepository<CartItem, Integer> {

	public List<CartItem> findByCustomer(Customer customer);
	
	public CartItem findByCustomerAndProduct(Customer customer, Product product);
	
	@Transactional
	public long deleteByCustomer(Customer customer);
}
