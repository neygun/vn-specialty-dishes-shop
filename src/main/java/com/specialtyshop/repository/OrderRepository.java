package com.specialtyshop.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.specialtyshop.entity.Customer;
import com.specialtyshop.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {

	public Page<Order> findByStatus(String status, Pageable pageable);
	
	public Page<Order> findByCustomer(Customer customer, Pageable pageable);
	
	public Page<Order> findByCustomerAndStatus(Customer customer, String status, Pageable pageable);
	
	@Query("select month(o.orderDate) as month, year(o.orderDate) as year, sum(o.amount) as sales "
			+ "from Order o "
			+ "where o.orderDate between ?1 and ?2 "
			+ "group by month(o.orderDate), year(o.orderDate)")
	public List<SalesReport> getSalesByMonth(Date startDate, Date endDate);
}
