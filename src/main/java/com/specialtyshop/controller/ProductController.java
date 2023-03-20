package com.specialtyshop.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.specialtyshop.entity.Product;
import com.specialtyshop.entity.Review;
import com.specialtyshop.service.ProductService;
import com.specialtyshop.service.ReviewService;

@Controller
@RequestMapping("/product")
public class ProductController {
	
	private ProductService productService;
	
	private ReviewService reviewService;
	
	@Autowired
	public ProductController(ProductService productService, ReviewService reviewService) {
		this.productService = productService;
		this.reviewService = reviewService;
	}
	
	@GetMapping("/list/all")
	public String listAllProducts(@RequestParam(name="minPrice", required=false) Double minPrice,
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
		model.addAttribute("totalItems", productPage.getTotalElements());
		
		model.addAttribute("minPrice", minPrice);
		model.addAttribute("maxPrice", maxPrice);
		model.addAttribute("sortBy", sortBy);
		
		return "product/list";
	}
	
	@GetMapping("/list/main-category/{mainCategoryId}")
	public String listByMainCategory(@PathVariable("mainCategoryId") Integer mainCategoryId,
			@RequestParam(name="minPrice", required=false) Double minPrice,
			@RequestParam(name="maxPrice", required=false) Double maxPrice,
			@RequestParam(name="sortBy", required=false) String sortBy,
			@RequestParam("page") Optional<Integer> page,
			Model model) {
		
		int currentPage = page.orElse(1);
		
		Page<Product> productPage = productService.findByMainCategoryId(mainCategoryId, minPrice, maxPrice, sortBy, currentPage);
		List<Product> products = productPage.getContent();
		model.addAttribute("products", products);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalPages", productPage.getTotalPages());
		model.addAttribute("totalItems", productPage.getTotalElements());
		
		model.addAttribute("minPrice", minPrice);
		model.addAttribute("maxPrice", maxPrice);
		model.addAttribute("sortBy", sortBy);
		
		return "product/list";
	}
	
	@GetMapping("/list/category/{categoryId}")
	public String listByCategory(@PathVariable("categoryId") Integer categoryId,
			@RequestParam(name="minPrice", required=false) Double minPrice,
			@RequestParam(name="maxPrice", required=false) Double maxPrice,
			@RequestParam(name="sortBy", required=false) String sortBy,
			@RequestParam("page") Optional<Integer> page,
			Model model) {
		
		int currentPage = page.orElse(1);
		
		Page<Product> productPage = productService.findBySubCategoryId(categoryId, minPrice, maxPrice, sortBy, currentPage);
		List<Product> products = productPage.getContent();
		model.addAttribute("products", products);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalPages", productPage.getTotalPages());
		model.addAttribute("totalItems", productPage.getTotalElements());
		
		model.addAttribute("minPrice", minPrice);
		model.addAttribute("maxPrice", maxPrice);
		model.addAttribute("sortBy", sortBy);
		
		return "product/list";
	}
	
	@GetMapping("/list/best-selling")
	public String listBestSellingProducts(@RequestParam(name="minPrice", required=false) Double minPrice,
			@RequestParam(name="maxPrice", required=false) Double maxPrice,
			@RequestParam(name="sortBy", required=false) String sortBy,
			@RequestParam("page") Optional<Integer> page,
			Model model) {
		
		int currentPage = page.orElse(1);
		
		Page<Product> productPage = productService.findBestSellingProducts(minPrice, maxPrice, sortBy, currentPage);
		List<Product> products = productPage.getContent();
		model.addAttribute("products", products);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalPages", productPage.getTotalPages());
		model.addAttribute("totalItems", productPage.getTotalElements());
		
		model.addAttribute("minPrice", minPrice);
		model.addAttribute("maxPrice", maxPrice);
		model.addAttribute("sortBy", sortBy);
		
		return "product/list";
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
		model.addAttribute("totalItems", productPage.getTotalElements());
		
		model.addAttribute("keyword", keyword);
		model.addAttribute("minPrice", minPrice);
		model.addAttribute("maxPrice", maxPrice);
		model.addAttribute("sortBy", sortBy);
		
		return "product/list";
	}

	@GetMapping("/detail/{productId}")
	public String viewDetail(@PathVariable("productId") Integer productId, 
			@RequestParam("page") Optional<Integer> page, 
			Model model) {
		
		int currentPage = page.orElse(1);
		
		Product product = productService.findById(productId);
		model.addAttribute("product", product);
		
		// get similar products
		List<Product> products = productService.findBySubCategoryId(product.getCategory().getId(), null, null, null, 1).getContent();
		model.addAttribute("products", products);
		
		Page<Review> reviewPage = reviewService.findByProduct(product, currentPage);
		List<Review> reviews = reviewPage.getContent();
		model.addAttribute("reviews", reviews);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalPages", reviewPage.getTotalPages());
		model.addAttribute("totalItems", reviewPage.getTotalElements());
		
		return "product/detail";
	}
}
