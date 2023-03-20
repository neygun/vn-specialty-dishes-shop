package com.specialtyshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.specialtyshop.entity.PurchaseOrder;

public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Integer> {

}
