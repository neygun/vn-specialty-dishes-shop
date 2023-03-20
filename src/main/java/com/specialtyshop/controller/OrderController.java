package com.specialtyshop.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.specialtyshop.entity.CartItem;
import com.specialtyshop.entity.Customer;
import com.specialtyshop.entity.Order;
import com.specialtyshop.service.CartService;
import com.specialtyshop.service.CustomerService;
import com.specialtyshop.service.OrderService;

@Controller
@RequestMapping("/order")
public class OrderController {

	private OrderService orderService;
	
	private CartService cartService;
	
	private CustomerService customerService;
	
	@Autowired
	public OrderController(OrderService orderService, CartService cartService, CustomerService customerService) {
		this.orderService = orderService;
		this.cartService = cartService;
		this.customerService = customerService;
	}

	@GetMapping("/checkout")
	public String showCheckoutForm(Model model, Principal principal) {
		
		String username = principal.getName();
		Customer customer = customerService.findByUsername(username);
		List<CartItem> cartItems = cartService.listCartItems(customer);
		double amount = cartService.getAmount(customer);
		
		Order order = new Order();
		order.setPhoneNumber(customer.getPhoneNumber());
		order.setAddress(customer.getAddress());
		
		model.addAttribute("cartItems", cartItems);
		model.addAttribute("subTotal", amount);
		model.addAttribute("order", order);
		return "order/checkout";
	}
	
	@PostMapping("/checkout")
	public String processCheckout(@ModelAttribute("order") Order order, 
			@RequestParam(name="couponId", required=false) Integer couponId, 
			Principal principal) {
		
		String username = principal.getName();
		Customer customer = customerService.findByUsername(username);
		if (!cartService.checkQuantity(customer)) {
			return "redirect:/cart";
		}
		orderService.saveOrder(order, customer, couponId);
		cartService.clearCart(customer);
		return "redirect:/order/checkout/success";
	}
	
	@GetMapping("/checkout/success")
	public String showSuccessPage() {
		return "order/success";
	}
	
	@GetMapping("/list")
    public String listCustomerOrders(@RequestParam("page") Optional<Integer> page, Model model, Principal principal) {
    	
		String username = principal.getName();
		Customer customer = customerService.findByUsername(username);
		
    	int currentPage = page.orElse(1);
    	Page<Order> orderPage = orderService.findByCustomer(customer, currentPage);
        List<Order> orders = orderPage.getContent();
        model.addAttribute("orders", orders);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalPages", orderPage.getTotalPages());
		
        return "order/list";
    }
	
	@GetMapping("/status/{status}")
    public String listByOrderStatus(@PathVariable("status") String status, 
    		@RequestParam("page") Optional<Integer> page, 
    		Model model, Principal principal) {
		
		String username = principal.getName();
		Customer customer = customerService.findByUsername(username);
    	
    	int currentPage = page.orElse(1);
    	Page<Order> orderPage = orderService.findByCustomerAndStatus(customer, status, currentPage);
        List<Order> orders = orderPage.getContent();
        model.addAttribute("orders", orders);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalPages", orderPage.getTotalPages());
		
        return "order/list";
    }
	
	@GetMapping("/search")
    public String searchOrder(@RequestParam("orderId") Integer orderId, Model model) {
    	
    	Order order = orderService.findById(orderId);
        model.addAttribute("orders", List.of(order));
        return "order/list";
    }
	
	@GetMapping("/detail/{orderId}")
    public String viewOrderDetail(@PathVariable("orderId") Integer orderId, Model model) {
    	
    	Order order = orderService.findById(orderId);
        model.addAttribute("order", order);
        model.addAttribute("orderDetails", order.getOrderDetails());
        return "order/detail";
    }
}
