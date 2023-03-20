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

import com.specialtyshop.entity.Category;
import com.specialtyshop.service.CategoryService;

@Controller
@RequestMapping("/admin/category")
public class AdminCategoryController {

	private CategoryService categoryService;

    @Autowired
    public AdminCategoryController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

    @GetMapping("/list")
    public String listCategories(@RequestParam("page") Optional<Integer> page, Model model) {
    	
    	int currentPage = page.orElse(1);
    	Page<Category> categoryPage = categoryService.findSubCategories(currentPage);
        List<Category> categories = categoryPage.getContent();
        model.addAttribute("categories", categories);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalPages", categoryPage.getTotalPages());
		
        return "admin/category-list";
    }

	@GetMapping("/add-form")
    public String showAddCategoryForm(Model model) {
        model.addAttribute("category", new Category());
        return "admin/category-form";
    }

    @PostMapping("/save")
    public String saveCategory(@ModelAttribute("category") Category category, 
    		RedirectAttributes redirectAttributes) {
    	
    	categoryService.save(category);
    	redirectAttributes.addFlashAttribute("message", "Cập nhật thành công.");
        return "redirect:/admin/category/list";
    }

    @GetMapping("/update-form")
    public String showUpdateCategoryForm(@RequestParam("categoryId") Integer categoryId, Model model) {
    	
    	Category category = categoryService.findById(categoryId);
        model.addAttribute("category", category);
        return "admin/category-form";
    }

    @GetMapping("/delete")
    public String deleteCategory(@RequestParam("categoryId") Integer categoryId, 
    		RedirectAttributes redirectAttributes) {
    	
    	categoryService.deleteById(categoryId);
    	redirectAttributes.addFlashAttribute("message", "Xóa thành công.");
        return "redirect:/admin/category/list";
    }
    
    @GetMapping("/main-category/{mainCategoryId}")
    public String listByMainCategory(@PathVariable("mainCategoryId") Integer mainCategoryId, 
    		@RequestParam("page") Optional<Integer> page, Model model) {
    	
    	int currentPage = page.orElse(1);
    	Page<Category> categoryPage = categoryService.findByMainCategoryId(mainCategoryId, currentPage);
        List<Category> categories = categoryPage.getContent();
        model.addAttribute("categories", categories);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalPages", categoryPage.getTotalPages());
		
        return "admin/category-list";
    }

    @GetMapping("/search")
    public String searchCategory(@RequestParam("keyword") String keyword, 
    		@RequestParam("page") Optional<Integer> page, Model model) {
    	
    	int currentPage = page.orElse(1);
    	Page<Category> categoryPage = categoryService.searchCategories(keyword, currentPage);
        List<Category> categories = categoryPage.getContent();
        model.addAttribute("categories", categories);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalPages", categoryPage.getTotalPages());
		model.addAttribute("keyword", keyword);
		
        return "admin/category-list";
    }
}
