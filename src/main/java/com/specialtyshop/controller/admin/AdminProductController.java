package com.specialtyshop.controller.admin;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.specialtyshop.entity.Category;
import com.specialtyshop.entity.Product;
import com.specialtyshop.entity.Supplier;
import com.specialtyshop.service.CategoryService;
import com.specialtyshop.service.ProductService;
import com.specialtyshop.service.SupplierService;

@Controller
@RequestMapping("/admin/product")
public class AdminProductController {

	private ProductService productService;
	
	private CategoryService categoryService;
	
	private SupplierService supplierService;
	
	private ServletContext app;
	
	@Autowired
	public AdminProductController(ProductService productService, CategoryService categoryService,
			SupplierService supplierService, ServletContext app) {
		this.productService = productService;
		this.categoryService = categoryService;
		this.supplierService = supplierService;
		this.app = app;
	}
	
	@GetMapping("/list")
    public String listProducts(@RequestParam(name="minPrice", required=false) Double minPrice,
			@RequestParam(name="maxPrice", required=false) Double maxPrice,
			@RequestParam(name="sortBy", required=false) String sortBy,
			@RequestParam("page") Optional<Integer> page,
			Model model) {
    	
		int currentPage = page.orElse(1);
		
		Page<Product> productPage = productService.findAllProducts(minPrice, maxPrice, sortBy, currentPage);
		List<Product> products = productPage.getContent();
        model.addAttribute("products", products);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalPages", productPage.getTotalPages());
		
		model.addAttribute("minPrice", minPrice);
		model.addAttribute("maxPrice", maxPrice);
		model.addAttribute("sortBy", sortBy);
		
        return "admin/product-list";
    }

	@GetMapping("/add-form")
    public String showAddProductForm(Model model) {
		
		List<Category> categories = categoryService.findSubCategories();
		List<Supplier> suppliers = supplierService.findAll();
		model.addAttribute("product", new Product());
        model.addAttribute("categories", categories);
        model.addAttribute("suppliers", suppliers);
        return "admin/product-form";
    }

    @PostMapping("/save")
    public String saveProduct(@ModelAttribute("product") Product product, 
    		@RequestParam("imageFile") MultipartFile imageFile, 
    		RedirectAttributes redirectAttributes) throws IllegalStateException, IOException {
    	
    	if (!imageFile.isEmpty()) {
    		product.setImage(imageFile.getOriginalFilename());
			String path = app.getRealPath("/static/images/products/" + product.getImage());
			imageFile.transferTo(new File(path));
		}
    	productService.save(product);
    	redirectAttributes.addFlashAttribute("message", "Cập nhật thành công.");
        return "redirect:/admin/product/list";
    }

    @GetMapping("/update-form")
    public String showUpdateProductForm(@RequestParam("productId") Integer productId, Model model) {
    	
    	Product product = productService.findById(productId);
    	List<Category> categories = categoryService.findSubCategories();
		List<Supplier> suppliers = supplierService.findAll();
        model.addAttribute("product", product);
        model.addAttribute("categories", categories);
        model.addAttribute("suppliers", suppliers);
        return "admin/product-form";
    }

    @GetMapping("/delete")
    public String deleteProduct(@RequestParam("productId") Integer productId, 
    		RedirectAttributes redirectAttributes) {
    	
    	productService.deleteById(productId);
    	redirectAttributes.addFlashAttribute("message", "Xóa thành công.");
        return "redirect:/admin/product/list";
    }

    @GetMapping("/search")
    public String searchProducts(@RequestParam("keyword") String keyword,
			@RequestParam(name="minPrice", required=false) Double minPrice,
			@RequestParam(name="maxPrice", required=false) Double maxPrice,
			@RequestParam(name="sortBy", required=false) String sortBy,
			@RequestParam("page") Optional<Integer> page,
			Model model) {
    	
    	int currentPage = page.orElse(1);
    	
		Page<Product> productPage = productService.searchProducts(keyword, minPrice, maxPrice, sortBy, currentPage);
		List<Product> products = productPage.getContent();
		model.addAttribute("products", products);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalPages", productPage.getTotalPages());
		
		model.addAttribute("keyword", keyword);
		model.addAttribute("minPrice", minPrice);
		model.addAttribute("maxPrice", maxPrice);
		model.addAttribute("sortBy", sortBy);
		
        return "admin/product-list";
    }
    
    @GetMapping("/view-purchase-order")
    public String showPurchaseOrderList(@RequestParam("productId") Integer productId, Model model) {
		
		Product product = productService.findById(productId);
        model.addAttribute("product", product);
        return "admin/view-purchase-order";
    }
    
    @GetMapping("/set-purchase-order")
    public String setCurrentPurchaseOrder(@RequestParam("productId") Integer productId, 
    		@RequestParam("purchaseOrderId") Integer purchaseOrderId, 
    		Model model) {
		
		productService.setCurrentPurchaseOrder(productId, purchaseOrderId);
        return "redirect:/admin/product/view-purchase-order?productId=" + productId;
    }
}
