package com.specialtyshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.specialtyshop.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	public Customer findByUsername(String username);
}
