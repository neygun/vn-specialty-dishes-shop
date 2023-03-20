package com.specialtyshop.controller.admin;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.specialtyshop.entity.Order;
import com.specialtyshop.service.OrderService;

@Controller
@RequestMapping("/admin/order")
public class AdminOrderController {

	private OrderService orderService;

	@Autowired
	public AdminOrderController(OrderService orderService) {
		this.orderService = orderService;
	}
	
	@GetMapping("/list")
    public String listOrders(@RequestParam("page") Optional<Integer> page, Model model) {
    	
    	int currentPage = page.orElse(1);
    	Page<Order> orderPage = orderService.findOrders(currentPage);
        List<Order> orders = orderPage.getContent();
        model.addAttribute("orders", orders);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalPages", orderPage.getTotalPages());
		
        return "admin/order-list";
    }
	
	@GetMapping("/status/{status}")
    public String listByOrderStatus(@PathVariable("status") String status, 
    		@RequestParam("page") Optional<Integer> page, 
    		Model model) {
    	
    	int currentPage = page.orElse(1);
    	Page<Order> orderPage = orderService.findByStatus(status, currentPage);
        List<Order> orders = orderPage.getContent();
        model.addAttribute("orders", orders);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalPages", orderPage.getTotalPages());
		
        return "admin/order-list";
    }
    
    @GetMapping("/search")
    public String searchOrder(@RequestParam("orderId") Integer orderId, Model model) {
    	
    	Order order = orderService.findById(orderId);
        model.addAttribute("orders", List.of(order));
		
        return "admin/order-list";
    }
    
    @GetMapping("/detail/{orderId}")
    public String viewOrderDetail(@PathVariable("orderId") Integer orderId, Model model) {
    	
    	Order order = orderService.findById(orderId);
        model.addAttribute("order", order);
        model.addAttribute("orderDetails", order.getOrderDetails());
        return "admin/order-detail";
    }
    
    @PostMapping("/update-status")
    public String updateOrderStatus(@ModelAttribute("order") Order form, RedirectAttributes redirectAttributes) {
    	
    	Order order = orderService.findById(form.getId());
    	order.setStatus(form.getStatus());
    	orderService.save(order);
    	redirectAttributes.addFlashAttribute("message", "Cập nhật thành công.");
        return "redirect:/admin/order/detail/" + order.getId();
    }
}
