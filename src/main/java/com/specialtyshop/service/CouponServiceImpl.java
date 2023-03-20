package com.specialtyshop.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.specialtyshop.entity.Coupon;
import com.specialtyshop.repository.CouponRepository;

@Service
public class CouponServiceImpl implements CouponService {

	private CouponRepository couponRepository;

	@Autowired
	public CouponServiceImpl(CouponRepository couponRepository) {
		this.couponRepository = couponRepository;
	}
	
	@Override
	public Coupon findById(Integer couponId) {
		
		Optional<Coupon> result = couponRepository.findById(couponId);
		Coupon coupon = null;
        if (result.isPresent()) {
        	coupon = result.get();
        } else {
            throw new RuntimeException("Did not find coupon id - " + couponId);
        }
        return coupon;
	}

	@Override
	public void save(Coupon coupon) {	
		couponRepository.save(coupon);
	}

	@Override
	public void deleteById(Integer couponId) {
		couponRepository.deleteById(couponId);
	}

	@Override
	public Coupon findByCode(String code) {
		return couponRepository.findByCode(code);
	}
	
	private Sort getSort(String sortBy) {
		
		Sort sort = null;
		if (sortBy == null || sortBy.equals("default")) {
			sort = Sort.unsorted();
		} else if (sortBy.equals("discount-desc")) {
			sort = Sort.by("discount").descending();
		} else if (sortBy.equals("discount-asc")) {
			sort = Sort.by("discount").ascending();
		} else if (sortBy.equals("limit-desc")) {
			sort = Sort.by("limit").descending();
		} else if (sortBy.equals("limit-asc")) {
			sort = Sort.by("limit").ascending();
		}
		return sort;
	}
	
	@Override
	public Page<Coupon> findCoupons(String sortBy, int page) {
		
		int pageSize = 10;
		Sort sort = getSort(sortBy);
		Pageable pageable = PageRequest.of(page-1, pageSize, sort);
		return couponRepository.findAll(pageable);
	}
	
	@Override
	public Page<Coupon> searchCoupons(String keyword, int page) {
		
		if (keyword != null && keyword.trim().length() > 0) {
			int pageSize = 10;
			Pageable pageable = PageRequest.of(page-1, pageSize);
            return couponRepository.findByKeyword(keyword, pageable);
        }
        return findCoupons(null, page);
	}
}
