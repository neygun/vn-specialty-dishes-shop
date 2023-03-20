package com.specialtyshop.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.specialtyshop.entity.Category;
import com.specialtyshop.entity.Customer;
import com.specialtyshop.service.CartService;
import com.specialtyshop.service.CategoryService;
import com.specialtyshop.service.CustomerService;

@ControllerAdvice
public class AddToView {
	
	private CategoryService categoryService;

	private CustomerService customerService;
	
	private CartService cartService;
	
	@Autowired
	public AddToView(CategoryService categoryService, CustomerService customerService, CartService cartService) {
		this.categoryService = categoryService;
		this.customerService = customerService;
		this.cartService = cartService;
	}

	@ModelAttribute
	public void addToView(Model model, Principal principal) {
		
		List<Category> mainCategories = categoryService.findMainCategories();
		Customer customer = null;
		int cartCount = 0;
		double cartAmount = 0;
		
		if (principal != null) {
			String username = principal.getName();
			customer = customerService.findByUsername(username);
			cartCount = cartService.getCount(customer);
			cartAmount = cartService.getAmount(customer);
		}
		
		model.addAttribute("mainCategories", mainCategories);
		model.addAttribute("customer", customer);
		model.addAttribute("cartCount", cartCount);
		model.addAttribute("cartAmount", cartAmount);
	}

	
}
