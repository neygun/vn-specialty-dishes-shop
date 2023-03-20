package com.specialtyshop.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.specialtyshop.entity.CartItem;
import com.specialtyshop.entity.Customer;
import com.specialtyshop.service.CartService;
import com.specialtyshop.service.CustomerService;

@Controller
@RequestMapping("/cart")
public class CartController {

	private CartService cartService;
	
	private CustomerService customerService;

	@Autowired
	public CartController(CartService cartService, CustomerService customerService) {
		this.cartService = cartService;
		this.customerService = customerService;
	}
	
	@GetMapping
	public String showCart(Model model, Principal principal) {
		
		String username = principal.getName();
		Customer customer = customerService.findByUsername(username);
		List<CartItem> cartItems = cartService.listCartItems(customer);
		model.addAttribute("cartItems", cartItems);
		return "cart/list";
	}
}
