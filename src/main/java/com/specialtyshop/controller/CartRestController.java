package com.specialtyshop.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.specialtyshop.entity.Customer;
import com.specialtyshop.service.CartService;
import com.specialtyshop.service.CustomerService;

@RestController
@RequestMapping("/cart")
public class CartRestController {

	private CartService cartService;
	
	private CustomerService customerService;

	@Autowired
	public CartRestController(CartService cartService, CustomerService customerService) {
		this.cartService = cartService;
		this.customerService = customerService;
	}
	
	@PostMapping("/add/{productId}/{quantity}")
	public Object[] addToCart(@PathVariable("productId") Integer productId, 
			@PathVariable("quantity") int quantity, 
			Principal principal) {
		
		String username = principal.getName();
		Customer customer = customerService.findByUsername(username);
		cartService.addToCart(productId, quantity, customer);
		
		int count = cartService.getCount(customer);
		double amount = cartService.getAmount(customer);
		Object[] info = {count, amount};
		return info;
	}
	
	@PostMapping("/update/{cartItemId}/{quantity}")
	public Object[] updateCartItem(@PathVariable("cartItemId") Integer cartItemId, 
			@PathVariable("quantity") int quantity, 
			Principal principal) {
		
		String username = principal.getName();
		Customer customer = customerService.findByUsername(username);
		
		double subtotal = cartService.updateCartItem(cartItemId, quantity);
		int count = cartService.getCount(customer);
		double amount = cartService.getAmount(customer);
		Object[] info = {count, amount, subtotal};
		return info;
	}
	
	@PostMapping("/remove/{cartItemId}")
	public Object[] removeCartItem(@PathVariable("cartItemId") Integer cartItemId, Principal principal) {
		
		String username = principal.getName();
		Customer customer = customerService.findByUsername(username);
		cartService.removeCartItem(cartItemId);
		
		int count = cartService.getCount(customer);
		double amount = cartService.getAmount(customer);
		Object[] info = {count, amount};
		return info;
	}
}
