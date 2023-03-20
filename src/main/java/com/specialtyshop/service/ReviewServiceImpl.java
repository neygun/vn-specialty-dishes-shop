package com.specialtyshop.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.specialtyshop.entity.Customer;
import com.specialtyshop.entity.Product;
import com.specialtyshop.entity.Review;
import com.specialtyshop.repository.CustomerRepository;
import com.specialtyshop.repository.ProductRepository;
import com.specialtyshop.repository.ReviewRepository;

@Service
public class ReviewServiceImpl implements ReviewService {
	
	private CustomerRepository customerRepository;
	
	private ProductRepository productRepository;
	
	private ReviewRepository reviewRepository;
	
	@Autowired
	public ReviewServiceImpl(CustomerRepository customerRepository, ProductRepository productRepository,
			ReviewRepository reviewRepository) {
		this.customerRepository = customerRepository;
		this.productRepository = productRepository;
		this.reviewRepository = reviewRepository;
	}

	@Override
	public Review addReview(String username, Integer productId, String content, Integer rating) {
		
		Customer customer = customerRepository.findByUsername(username);
		Product product = productRepository.findById(productId).get();
		
		Review review = new Review();
		review.setCustomer(customer);
		review.setProduct(product);
		review.setContent(content);
		review.setRating(rating);
		review.setReviewDate(new Date());
		return reviewRepository.save(review);
	}

	@Override
	public Page<Review> findByProduct(Product product, int page) {
		
		int pageSize = 3;
		Sort sort = Sort.by("reviewDate").descending();
		Pageable pageable = PageRequest.of(page-1, pageSize, sort);
		return reviewRepository.findByProduct(product, pageable);
	}

}
