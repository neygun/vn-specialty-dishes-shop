package com.specialtyshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.specialtyshop.entity.PurchaseOrderDetail;
import com.specialtyshop.repository.PurchaseOrderDetailRepository;

@Service
public class PurchaseOrderDetailServiceImpl implements PurchaseOrderDetailService {

	private PurchaseOrderDetailRepository purchaseOrderDetailRepository;

	@Autowired
	public PurchaseOrderDetailServiceImpl(PurchaseOrderDetailRepository purchaseOrderDetailRepository) {
		this.purchaseOrderDetailRepository = purchaseOrderDetailRepository;
	}

	@Override
	public void save(PurchaseOrderDetail pod) {
		purchaseOrderDetailRepository.save(pod);
	}
	
	
}
