package com.specialtyshop.service;

import org.springframework.data.domain.Page;

import com.specialtyshop.entity.Coupon;

public interface CouponService {
	
	public Coupon findById(Integer couponId);
	
	public void save(Coupon coupon);
	
	public void deleteById(Integer couponId);

	public Coupon findByCode(String code);
	
	public Page<Coupon> findCoupons(String sortBy, int page);
	
	public Page<Coupon> searchCoupons(String keyword, int page);
}
