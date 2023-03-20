package com.specialtyshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.specialtyshop.entity.Product;
import com.specialtyshop.entity.Supplier;

public interface ProductRepository extends JpaRepository<Product, Integer>, JpaSpecificationExecutor<Product> {
	
	public List<Product> findBySupplier(Supplier supplier);
}
