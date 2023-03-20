package com.specialtyshop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.specialtyshop.entity.Category;
import com.specialtyshop.repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {

	private CategoryRepository categoryRepository;

	@Autowired
	public CategoryServiceImpl(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

	@Override
	public List<Category> findAll() {
		return categoryRepository.findAll();
	}

	@Override
	public Category findById(Integer id) {
		
		Optional<Category> result = categoryRepository.findById(id);
		Category category = null;
        if (result.isPresent()) {
        	category = result.get();
        } else {
            throw new RuntimeException("Did not find category id - " + id);
        }
        return category;
	}

	@Override
	public void save(Category category) {
		categoryRepository.save(category);
	}

	@Override
	public List<Category> findMainCategories() {
		return categoryRepository.findByParentIsNull();
	}
	
	@Override
	public List<Category> findSubCategories() {
		return categoryRepository.findByParentIsNotNull();
	}

	@Override
	public void deleteById(Integer id) {
		categoryRepository.deleteById(id);
	}

	@Override
	public Page<Category> findSubCategories(int page) {
		
		int pageSize = 10;
		Sort sort = Sort.by("name").ascending();
		Pageable pageable = PageRequest.of(page-1, pageSize, sort);
		return categoryRepository.findByParentIsNotNull(pageable);
	}
	
	@Override
	public Page<Category> findByMainCategoryId(Integer mainCategoryId, int page) {
		
		int pageSize = 10;
		Sort sort = Sort.by("name").ascending();
		Pageable pageable = PageRequest.of(page-1, pageSize, sort);
		return categoryRepository.findByMainCategoryId(mainCategoryId, pageable);
	}

	@Override
	public Page<Category> searchCategories(String keyword, int page) {
		
		if (keyword != null && keyword.trim().length() > 0) {
			int pageSize = 10;
			Sort sort = Sort.by("name").ascending();
			Pageable pageable = PageRequest.of(page-1, pageSize, sort);
            return categoryRepository.findByKeyword(keyword, pageable);
        }
        return findSubCategories(page);
	}

}
