package com.specialtyshop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.specialtyshop.entity.CartItem;
import com.specialtyshop.entity.Customer;
import com.specialtyshop.entity.Product;
import com.specialtyshop.repository.CartItemRepository;
import com.specialtyshop.repository.ProductRepository;

@Service
public class CartServiceImpl implements CartService {

	private CartItemRepository cartItemRepository;
	
	private ProductRepository productRepository;

	@Autowired
	public CartServiceImpl(CartItemRepository cartItemRepository, ProductRepository productRepository) {
		this.cartItemRepository = cartItemRepository;
		this.productRepository = productRepository;
	}

	@Override
	public List<CartItem> listCartItems(Customer customer) {
		return cartItemRepository.findByCustomer(customer);
	}
	
	@Override
	public int getCount(Customer customer) {
		
		int count = 0;
		List<CartItem> cartItems = listCartItems(customer);
		for (CartItem cartItem : cartItems) {
			count += cartItem.getQuantity();
		}
		return count;
	}

	@Override
	public double getAmount(Customer customer) {
		
		double amount = 0;
		List<CartItem> cartItems = listCartItems(customer);
		for (CartItem cartItem : cartItems) {
			amount += cartItem.getSubtotal();
		}
		return amount;
	}

	@Override
	public void addToCart(Integer productId, int quantity, Customer customer) {
		
		Product product = productRepository.findById(productId).get();
		CartItem cartItem = cartItemRepository.findByCustomerAndProduct(customer, product);
		
		if (cartItem != null) {
			cartItem.setQuantity(cartItem.getQuantity() + quantity);
		} else {
			cartItem = new CartItem();
			cartItem.setCustomer(customer);
			cartItem.setProduct(product);
			cartItem.setQuantity(quantity);
		}
		cartItemRepository.save(cartItem);
	}

	@Override
	public double updateCartItem(Integer cartItemId, int quantity) {
		
		CartItem cartItem = cartItemRepository.findById(cartItemId).get();
		cartItem.setQuantity(quantity);
		cartItemRepository.save(cartItem);
		
		double subtotal = cartItem.getSubtotal();
		return subtotal;
	}

	@Override
	public void removeCartItem(Integer cartItemId) {
		cartItemRepository.deleteById(cartItemId);
	}

	@Override
	public boolean checkQuantity(Customer customer) {
		
		List<CartItem> cartItems = listCartItems(customer);
		for (CartItem cartItem : cartItems) {
			if (cartItem.getQuantity() > cartItem.getProduct().getQuantity()) {
				return false;
			}
		}
		return true;
	}

	@Override
	public void clearCart(Customer customer) {
		cartItemRepository.deleteByCustomer(customer);
	}

}
