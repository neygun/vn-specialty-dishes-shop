package com.specialtyshop.service;

import java.util.Arrays;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.specialtyshop.entity.Account;
import com.specialtyshop.entity.Customer;
import com.specialtyshop.repository.AccountRepository;
import com.specialtyshop.repository.CustomerRepository;
import com.specialtyshop.repository.RoleRepository;

@Service
public class AccountServiceImpl implements AccountService {

	private AccountRepository accountRepository;
	
	private RoleRepository roleRepository;
	
	private PasswordEncoder passwordEncoder;
	
	private VerificationTokenService verificationTokenService;
	
	private EmailService emailService;
	
	private CustomerRepository customerRepository;
	
	@Autowired
	public AccountServiceImpl(AccountRepository accountRepository, RoleRepository roleRepository,
			PasswordEncoder passwordEncoder, VerificationTokenService verificationTokenService,
			EmailService emailService, CustomerRepository customerRepository) {
		this.accountRepository = accountRepository;
		this.roleRepository = roleRepository;
		this.passwordEncoder = passwordEncoder;
		this.verificationTokenService = verificationTokenService;
		this.emailService = emailService;
		this.customerRepository = customerRepository;
	}
	
	@Override
	public Account save(Account account) {
		return accountRepository.save(account);
	}

	@Override
	public void register(Account account) {
		
		account.setPassword(passwordEncoder.encode(account.getPassword()));
		account.setEnabled(false);
		account.setRoles(Arrays.asList(roleRepository.findByName("ROLE_CUSTOMER")));
		Account savedAccount = save(account);
		
		// create a customer
		Customer customer = new Customer();
		customer.setUsername(account.getUsername());
		customer.setAvatar("user.jpg");
		customerRepository.save(customer);
		
		try {
			String token = UUID.randomUUID().toString();
			verificationTokenService.save(savedAccount, token);
			emailService.sendMail(savedAccount);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public boolean usernameExists(String username) {
        return accountRepository.findByUsername(username) != null;
    }
}
