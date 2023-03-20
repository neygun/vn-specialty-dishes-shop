package com.specialtyshop.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.specialtyshop.entity.CartItem;
import com.specialtyshop.entity.Coupon;
import com.specialtyshop.entity.Customer;
import com.specialtyshop.entity.Order;
import com.specialtyshop.entity.OrderDetail;
import com.specialtyshop.entity.Product;
import com.specialtyshop.entity.PurchaseOrder;
import com.specialtyshop.entity.PurchaseOrderDetail;
import com.specialtyshop.repository.CouponRepository;
import com.specialtyshop.repository.OrderDetailRepository;
import com.specialtyshop.repository.OrderRepository;
import com.specialtyshop.repository.ProductRepository;
import com.specialtyshop.repository.PurchaseOrderDetailRepository;
import com.specialtyshop.repository.PurchaseOrderRepository;
import com.specialtyshop.repository.SalesReport;

@Service
public class OrderServiceImpl implements OrderService {
	
	private CartService cartService;

	private OrderRepository orderRepository;
	
	private OrderDetailRepository orderDetailRepository;
	
	private ProductRepository productRepository;
	
	private PurchaseOrderRepository purchaseOrderRepository;
	
	private PurchaseOrderDetailRepository podRepository;
	
	private CouponRepository couponRepository;
	
	@Autowired
	public OrderServiceImpl(CartService cartService, OrderRepository orderRepository,
			OrderDetailRepository orderDetailRepository, ProductRepository productRepository,
			PurchaseOrderRepository purchaseOrderRepository, PurchaseOrderDetailRepository podRepository,
			CouponRepository couponRepository) {
		this.cartService = cartService;
		this.orderRepository = orderRepository;
		this.orderDetailRepository = orderDetailRepository;
		this.productRepository = productRepository;
		this.purchaseOrderRepository = purchaseOrderRepository;
		this.podRepository = podRepository;
		this.couponRepository = couponRepository;
	}

	@Override
	public void saveOrder(Order order, Customer customer, Integer couponId) {
		
		if (couponId != null) {
			Coupon coupon = couponRepository.findById(couponId).get();
			order.setCoupon(coupon);
			order.setDiscount(coupon.getDiscount());
			coupon.setLimit(coupon.getLimit()-1);
			couponRepository.save(coupon);
		}
		double amount = cartService.getAmount(customer);
		order.setAmount(amount*(1-order.getDiscount()));
		order.setCustomer(customer);
		order.setOrderDate(new Date());
		order.setStatus("Chờ xác nhận");
		Order savedOrder = orderRepository.save(order);
		
		List<CartItem> cartItems = cartService.listCartItems(customer);
		for (CartItem cartItem : cartItems) {
			OrderDetail orderDetail = new OrderDetail();
			orderDetail.setOrder(savedOrder);
			
			Product product = cartItem.getProduct();
			orderDetail.setProduct(product);
			
			PurchaseOrder purchaseOrder = purchaseOrderRepository.findById(product.getCurrentPO()).get();
			orderDetail.setPurchaseOrder(purchaseOrder);
			
			orderDetail.setUnitPrice(product.getUnitPrice());
			orderDetail.setDiscount(product.getDiscount());
			int quantity = cartItem.getQuantity();
			orderDetail.setQuantity(quantity);
			orderDetailRepository.save(orderDetail);
			
			PurchaseOrderDetail pod = podRepository.findByPurchaseOrderAndProduct(purchaseOrder, product);
			pod.setSold(pod.getSold() + quantity);
			podRepository.save(pod);
			
			product.setQuantity(product.getQuantity() - quantity);
			if (product.getQuantity() == 0) {
				product.setAvailable(false);
			}
			productRepository.save(product);
		}
	}

	@Override
	public Order findById(Integer orderId) {
		
		Optional<Order> result = orderRepository.findById(orderId);
		Order order = null;
        if (result.isPresent()) {
        	order = result.get();
        } else {
            throw new RuntimeException("Did not find order id - " + orderId);
        }
        return order;
	}

	@Override
	public Page<Order> findOrders(int page) {
		
		int pageSize = 10;
		Sort sort = Sort.by("orderDate").descending();
		Pageable pageable = PageRequest.of(page-1, pageSize, sort);
		return orderRepository.findAll(pageable);
	}

	@Override
	public Page<Order> findByStatus(String status, int page) {
		
		int pageSize = 10;
		Sort sort = Sort.by("orderDate").descending();
		Pageable pageable = PageRequest.of(page-1, pageSize, sort);
		String orderStatus = null;
		if (status.equals("pending")) {
			orderStatus = "Chờ xác nhận";
		} else if (status.equals("shipping")) {
			orderStatus = "Đang giao";
		} else if (status.equals("completed")) {
			orderStatus = "Đã giao";
		}
		return orderRepository.findByStatus(orderStatus, pageable);
	}

	@Override
	public Page<Order> findByCustomer(Customer customer, int page) {
		
		int pageSize = 10;
		Sort sort = Sort.by("orderDate").descending();
		Pageable pageable = PageRequest.of(page-1, pageSize, sort);
		return orderRepository.findByCustomer(customer, pageable);
	}

	@Override
	public Page<Order> findByCustomerAndStatus(Customer customer, String status, int page) {
		
		int pageSize = 10;
		Sort sort = Sort.by("orderDate").descending();
		Pageable pageable = PageRequest.of(page-1, pageSize, sort);
		String orderStatus = null;
		if (status.equals("pending")) {
			orderStatus = "Chờ xác nhận";
		} else if (status.equals("shipping")) {
			orderStatus = "Đang giao";
		} else if (status.equals("completed")) {
			orderStatus = "Đã giao";
		}
		return orderRepository.findByCustomerAndStatus(customer, orderStatus, pageable);
	}

	@Override
	public void save(Order order) {
		orderRepository.save(order);
	}

	@Override
	public List<SalesReport> getSalesByMonth(Date startDate, Date endDate) {
		return orderRepository.getSalesByMonth(startDate, endDate);
	}

	
}
