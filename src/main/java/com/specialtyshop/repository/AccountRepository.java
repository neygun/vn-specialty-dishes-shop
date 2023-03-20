package com.specialtyshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.specialtyshop.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Integer> {

	public Account findByUsername(String username);
}
