package com.specialtyshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.specialtyshop.entity.Product;
import com.specialtyshop.entity.PurchaseOrder;
import com.specialtyshop.entity.PurchaseOrderDetail;

public interface PurchaseOrderDetailRepository extends JpaRepository<PurchaseOrderDetail, Integer> {

	@Query("select p from PurchaseOrderDetail p where p.purchaseOrder.id = ?1 and p.product.id = ?2")
	public PurchaseOrderDetail findByPurchaseOrderIdAndProductId(Integer purchaseOrderId, Integer productId);
	
	public PurchaseOrderDetail findByPurchaseOrderAndProduct(PurchaseOrder purchaseOrder, Product product);
}
