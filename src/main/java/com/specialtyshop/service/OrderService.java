package com.specialtyshop.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;

import com.specialtyshop.entity.Customer;
import com.specialtyshop.entity.Order;
import com.specialtyshop.repository.SalesReport;

public interface OrderService {

	public void saveOrder(Order order, Customer customer, Integer couponId);
	
	public Order findById(Integer orderId);
	
	public Page<Order> findOrders(int page);
	
	public Page<Order> findByStatus(String status, int page);
	
	public Page<Order> findByCustomer(Customer customer, int page);
	
	public Page<Order> findByCustomerAndStatus(Customer customer, String status, int page);
	
	public void save(Order order);
	
	public List<SalesReport> getSalesByMonth(Date startDate, Date endDate);
}
