package com.specialtyshop.service;

import org.springframework.data.domain.Page;

import com.specialtyshop.entity.PurchaseOrder;

public interface PurchaseOrderService {

	public PurchaseOrder findById(Integer purchaseOrderId);
	
	public PurchaseOrder save(PurchaseOrder purchaseOrder);
	
	public void deleteById(Integer purchaseOrderId);
		
	public Page<PurchaseOrder> findPurchaseOrders(int page);
	
	public void updateAmount(Integer purchaseOrderId);
}
