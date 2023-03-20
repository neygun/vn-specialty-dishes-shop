package com.specialtyshop.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.specialtyshop.entity.Customer;
import com.specialtyshop.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	private CustomerService customerService;
	
	private ServletContext app;

	@Autowired
	public CustomerController(CustomerService customerService, ServletContext app) {
		this.customerService = customerService;
		this.app = app;
	}
	
	@GetMapping("/profile")
	public String viewProfile() {
		return "customer/profile";
	}

	@PostMapping("/profile")
	public String updateProfile(Model model, @ModelAttribute("customer") Customer customer, 
			@RequestParam("imageFile") MultipartFile imageFile) throws IllegalStateException, IOException {
		
		if (!imageFile.isEmpty()) {
			String dir = app.getRealPath("/static/images/customers");
			File f = new File(dir, imageFile.getOriginalFilename());
			imageFile.transferTo(f);
			customer.setAvatar(f.getName());
		}
		customerService.save(customer);
		model.addAttribute("message", "Cập nhật thông tin thành công");
		
		return "customer/profile";
	}
}
