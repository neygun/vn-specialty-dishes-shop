package com.specialtyshop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.specialtyshop.entity.Post;
import com.specialtyshop.entity.Product;
import com.specialtyshop.service.PostService;
import com.specialtyshop.service.ProductService;

@Controller
public class HomeController {
	
	private ProductService productService;
	
	private PostService postService;
	
	@Autowired
	public HomeController(ProductService productService, PostService postService) {
		this.productService = productService;
		this.postService = postService;
	}

	@RequestMapping("/")
	public String index(Model model) {
		
		List<Product> mienBac = productService.findByMainCategoryId(1, null, null, null, 1).getContent();
		List<Product> mienTrung = productService.findByMainCategoryId(2, null, null, null, 1).getContent();
		List<Product> mienNam = productService.findByMainCategoryId(3, null, null, null, 1).getContent();
		List<Post> posts = postService.findPosts("date-desc", 1).getContent();
		
		model.addAttribute("mienBac", mienBac);
		model.addAttribute("mienTrung", mienTrung);
		model.addAttribute("mienNam", mienNam);
		model.addAttribute("posts", posts);
		return "home/index";
	}

	@RequestMapping("/about")
	public String about() {
		return "home/about";
	}
}
