package com.specialtyshop.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.specialtyshop.entity.Supplier;

public interface SupplierService {
	
	public Supplier findById(Integer supplierId);
	
	public void save(Supplier supplier);
	
	public void deleteById(Integer supplierId);
	
	public List<Supplier> findAll();
	
	public Page<Supplier> findSuppliers(int page);
	
	public Page<Supplier> searchSuppliers(String keyword, int page);
	
}
