package com.specialtyshop.controller.admin;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.specialtyshop.repository.SalesReport;
import com.specialtyshop.service.OrderService;

@Controller
@RequestMapping("/admin/report")
public class AdminReportController {
	
	private OrderService orderService;
	
	@Autowired
	public AdminReportController(OrderService orderService) {
		this.orderService = orderService;
	}
	
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    dateFormat.setLenient(false);
	    dataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}

	@GetMapping("/sales")
	public String reportSalesByMonth(@RequestParam(name="startDate", required=false) Date startDate, 
			@RequestParam(name="endDate", required=false) Date endDate, 
			Model model) {
		
		List<SalesReport> salesReports = orderService.getSalesByMonth(startDate, endDate);
		model.addAttribute("salesReports", salesReports);
		return "admin/sales-report";
	}
}
