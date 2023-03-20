package com.specialtyshop.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.specialtyshop.entity.PurchaseOrder;
import com.specialtyshop.entity.PurchaseOrderDetail;
import com.specialtyshop.repository.PurchaseOrderRepository;

@Service
public class PurchaseOrderServiceImpl implements PurchaseOrderService {
	
	private PurchaseOrderRepository purchaseOrderRepository;
	
	@Autowired
	public PurchaseOrderServiceImpl(PurchaseOrderRepository purchaseOrderRepository) {
		this.purchaseOrderRepository = purchaseOrderRepository;
	}

	@Override
	public PurchaseOrder findById(Integer purchaseOrderId) {
		
		Optional<PurchaseOrder> result = purchaseOrderRepository.findById(purchaseOrderId);
		PurchaseOrder purchaseOrder = null;
        if (result.isPresent()) {
        	purchaseOrder = result.get();
        } else {
            throw new RuntimeException("Did not find purchase order id - " + purchaseOrderId);
        }
        return purchaseOrder;
	}

	@Override
	public PurchaseOrder save(PurchaseOrder purchaseOrder) {
		return purchaseOrderRepository.save(purchaseOrder);
	}

	@Override
	public void deleteById(Integer purchaseOrderId) {
		purchaseOrderRepository.deleteById(purchaseOrderId);
	}

	@Override
	public Page<PurchaseOrder> findPurchaseOrders(int page) {
		
		int pageSize = 5;
		Sort sort = Sort.by("purchaseDate").descending();
		Pageable pageable = PageRequest.of(page-1, pageSize, sort);
		return purchaseOrderRepository.findAll(pageable);
	}

	@Override
	public void updateAmount(Integer purchaseOrderId) {
		
		PurchaseOrder purchaseOrder = findById(purchaseOrderId);
		double amount = 0;
		for (PurchaseOrderDetail pod : purchaseOrder.getPurchaseOrderDetails()) {
	    	amount += pod.getQuantity()*pod.getUnitPrice();
		}
		purchaseOrder.setAmount(amount);
		save(purchaseOrder);
	}

}
