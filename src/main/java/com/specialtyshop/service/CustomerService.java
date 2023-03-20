package com.specialtyshop.service;

import com.specialtyshop.entity.Customer;

public interface CustomerService {
	
	public Customer findByUsername(String username);

	public void save(Customer customer);
}
