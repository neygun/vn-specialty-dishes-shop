package com.specialtyshop.controller.admin;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.specialtyshop.entity.Supplier;
import com.specialtyshop.service.SupplierService;

@Controller
@RequestMapping("/admin/supplier")
public class AdminSupplierController {

	private SupplierService supplierService;

	@Autowired
	public AdminSupplierController(SupplierService supplierService) {
		this.supplierService = supplierService;
	}
	
	@GetMapping("/list")
    public String listSuppliers(@RequestParam("page") Optional<Integer> page, Model model) {
    	
    	int currentPage = page.orElse(1);
    	Page<Supplier> supplierPage = supplierService.findSuppliers(currentPage);
        List<Supplier> suppliers = supplierPage.getContent();
        model.addAttribute("suppliers", suppliers);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalPages", supplierPage.getTotalPages());
		
        return "admin/supplier-list";
    }

	@GetMapping("/add-form")
    public String showAddSupplierForm(Model model) {
        model.addAttribute("supplier", new Supplier());
        return "admin/supplier-form";
    }

    @PostMapping("/save")
    public String saveSupplier(@ModelAttribute("supplier") Supplier supplier, 
    		RedirectAttributes redirectAttributes) {
    	
    	supplierService.save(supplier);
    	redirectAttributes.addFlashAttribute("message", "Cập nhật thành công.");
        return "redirect:/admin/supplier/list";
    }

    @GetMapping("/update-form")
    public String showUpdateSupplierForm(@RequestParam("supplierId") Integer supplierId, Model model) {
    	
    	Supplier supplier = supplierService.findById(supplierId);
        model.addAttribute("supplier", supplier);
        return "admin/supplier-form";
    }

    @GetMapping("/delete")
    public String deleteSupplier(@RequestParam("supplierId") Integer supplierId, 
    		RedirectAttributes redirectAttributes) {
    	
    	supplierService.deleteById(supplierId);
    	redirectAttributes.addFlashAttribute("message", "Xóa thành công.");
        return "redirect:/admin/supplier/list";
    }
    
    @GetMapping("/search")
    public String searchSuppliers(@RequestParam("keyword") String keyword, 
    		@RequestParam("page") Optional<Integer> page, Model model) {
    	
    	int currentPage = page.orElse(1);
    	
    	Page<Supplier> supplierPage = supplierService.searchSuppliers(keyword, currentPage);
    	List<Supplier> suppliers = supplierPage.getContent();
        model.addAttribute("suppliers", suppliers);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalPages", supplierPage.getTotalPages());
		model.addAttribute("keyword", keyword);
		
        return "admin/supplier-list";
    }
}
