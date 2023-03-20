package com.specialtyshop.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.specialtyshop.entity.Category;

public interface CategoryService {

	public List<Category> findAll();
	
	public Category findById(Integer id);
	
	public void save(Category category);
	
	public void deleteById(Integer id);
	
	public List<Category> findMainCategories();
	
	public List<Category> findSubCategories();
	
	public Page<Category> findSubCategories(int page);
	
	public Page<Category> findByMainCategoryId(Integer mainCategoryId, int page);
	
	public Page<Category> searchCategories(String keyword, int page);
}
