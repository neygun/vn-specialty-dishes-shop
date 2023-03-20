package com.specialtyshop.service;

import com.specialtyshop.entity.Account;

public interface AccountService {
	
	public Account save(Account account);

	public void register(Account account);
	
	public boolean usernameExists(String username);
}
