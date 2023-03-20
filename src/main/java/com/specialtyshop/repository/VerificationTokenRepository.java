package com.specialtyshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.specialtyshop.entity.Account;
import com.specialtyshop.entity.VerificationToken;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Integer> {

	public VerificationToken findByToken(String token);
	
	public VerificationToken findByAccount(Account account);
}
