package com.specialtyshop.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.specialtyshop.entity.Coupon;
import com.specialtyshop.service.CouponService;

@RestController
@RequestMapping("/coupon")
public class CouponRestController {

	private CouponService couponService;

	@Autowired
	public CouponRestController(CouponService couponService) {
		this.couponService = couponService;
	}
	
	@PostMapping("/apply/{couponCode}")
	public Object[] applyCoupon(@PathVariable("couponCode") String couponCode) {
		
		Coupon coupon = couponService.findByCode(couponCode);
		Integer id = null;
		String message = "";
		double discount = 0;
		Date currentDate = new Date();
		if (coupon == null) {
			message = "Mã không hợp lệ.";
		} else if (coupon.getLimit() == 0) {
			message = "Mã đã hết số lần sử dụng.";
		} else if (currentDate.before(coupon.getStartDate()) || currentDate.after(coupon.getEndDate())) {
			message = "Mã không trong thời gian khuyến mãi.";
		} else {
			id = coupon.getId();
			discount = coupon.getDiscount();
		}
		Object[] info = {message, discount, id};
		return info;
	}
}
