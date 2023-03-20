package com.specialtyshop.service;

import com.specialtyshop.entity.Account;
import com.specialtyshop.entity.VerificationToken;

public interface VerificationTokenService {

	public VerificationToken findByToken(String token);
	
	public VerificationToken findByAccount(Account account);
	
	public void save(Account account, String token);
}
