package com.specialtyshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.specialtyshop.entity.Admin;
import com.specialtyshop.repository.AdminRepository;

@Service
public class AdminServiceImpl implements AdminService {
	
	private AdminRepository adminRepository;
	
	@Autowired
	public AdminServiceImpl(AdminRepository adminRepository) {
		this.adminRepository = adminRepository;
	}

	@Override
	public void save(Admin admin) {
		adminRepository.save(admin);
	}
}
