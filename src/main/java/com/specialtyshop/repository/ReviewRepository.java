package com.specialtyshop.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.specialtyshop.entity.Product;
import com.specialtyshop.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Integer> {

	public Page<Review> findByProduct(Product product, Pageable pageable);
}
