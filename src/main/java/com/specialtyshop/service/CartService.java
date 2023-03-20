package com.specialtyshop.service;

import java.util.List;

import com.specialtyshop.entity.CartItem;
import com.specialtyshop.entity.Customer;

public interface CartService {

	public List<CartItem> listCartItems(Customer customer);
	
	public int getCount(Customer customer);
	
	public double getAmount(Customer customer);
	
	public void addToCart(Integer productId, int quantity, Customer customer);
	
	public double updateCartItem(Integer cartItemId, int quantity);
	
	public void removeCartItem(Integer cartItemId);
	
	public boolean checkQuantity(Customer customer);
	
	public void clearCart(Customer customer);
}
