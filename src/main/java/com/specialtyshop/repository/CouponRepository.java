package com.specialtyshop.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.specialtyshop.entity.Coupon;

public interface CouponRepository extends JpaRepository<Coupon, Integer> {

	public Coupon findByCode(String code);
	
	@Query(value = "SELECT * FROM coupon WHERE name LIKE %?1% OR code LIKE %?1%", 
			countQuery = "SELECT count(*) FROM coupon WHERE name LIKE %?1% OR code LIKE %?1%", 
			nativeQuery = true)
    public Page<Coupon> findByKeyword(String keyword, Pageable pageable);
}
