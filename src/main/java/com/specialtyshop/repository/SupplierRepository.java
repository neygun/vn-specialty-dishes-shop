package com.specialtyshop.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.specialtyshop.entity.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier, Integer> {

	@Query(value = "SELECT * FROM supplier WHERE name LIKE %?1%", 
			countQuery = "SELECT count(*) FROM supplier WHERE name LIKE %?1%", 
			nativeQuery = true)
    public Page<Supplier> findByKeyword(String keyword, Pageable pageable);
}
