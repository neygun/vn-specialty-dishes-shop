package com.specialtyshop.controller;

import java.security.Principal;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.specialtyshop.entity.Review;
import com.specialtyshop.service.ReviewService;

@RestController
@RequestMapping("/review")
public class ReviewRestController {
	
	private ReviewService reviewService;
	
	@Autowired
	public ReviewRestController(ReviewService reviewService) {
		this.reviewService = reviewService;
	}

	@PostMapping("/add")
	public Review addReview(@RequestBody Map<String, String> params, Principal principal) {
		
		String username = principal.getName();
		
		try {
			String content = params.get("content");
			Integer productId = Integer.parseInt(params.get("productId"));
			Integer rating = Integer.parseInt(params.get("rating"));
			Review review = reviewService.addReview(username, productId, content, rating);
			return review;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
}
