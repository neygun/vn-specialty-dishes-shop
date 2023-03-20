package com.specialtyshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.specialtyshop.entity.OrderDetail;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {

}
