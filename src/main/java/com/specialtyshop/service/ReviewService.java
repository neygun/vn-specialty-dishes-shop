package com.specialtyshop.service;

import org.springframework.data.domain.Page;

import com.specialtyshop.entity.Product;
import com.specialtyshop.entity.Review;

public interface ReviewService {

	public Review addReview(String username, Integer productId, String content, Integer rating);
	
	public Page<Review> findByProduct(Product product, int page);
}
