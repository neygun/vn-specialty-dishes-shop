package com.specialtyshop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.specialtyshop.entity.Supplier;
import com.specialtyshop.repository.SupplierRepository;

@Service
public class SupplierServiceImpl implements SupplierService {

	private SupplierRepository supplierRepository;
	
	@Autowired
	public SupplierServiceImpl(SupplierRepository supplierRepository) {
		this.supplierRepository = supplierRepository;
	}

	@Override
	public Supplier findById(Integer supplierId) {
		
		Optional<Supplier> result = supplierRepository.findById(supplierId);
		Supplier supplier = null;
        if (result.isPresent()) {
        	supplier = result.get();
        } else {
            throw new RuntimeException("Did not find supplier id - " + supplierId);
        }
        return supplier;
	}

	@Override
	public void save(Supplier supplier) {
		supplierRepository.save(supplier);
	}

	@Override
	public void deleteById(Integer supplierId) {
		supplierRepository.deleteById(supplierId);
	}
	
	@Override
	public List<Supplier> findAll() {
		return supplierRepository.findAll();
	}

	@Override
	public Page<Supplier> findSuppliers(int page) {
		
		int pageSize = 5;
		Sort sort = Sort.by("name").ascending();
		Pageable pageable = PageRequest.of(page-1, pageSize, sort);
		return supplierRepository.findAll(pageable);
	}

	@Override
	public Page<Supplier> searchSuppliers(String keyword, int page) {
		
		if (keyword != null && keyword.trim().length() > 0) {
			int pageSize = 5;
			Sort sort = Sort.by("name").ascending();
			Pageable pageable = PageRequest.of(page-1, pageSize, sort);
            return supplierRepository.findByKeyword(keyword, pageable);
        }
        return findSuppliers(page);
	}

}
