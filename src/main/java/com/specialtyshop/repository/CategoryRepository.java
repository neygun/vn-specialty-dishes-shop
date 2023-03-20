package com.specialtyshop.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.specialtyshop.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
	
	public List<Category> findByParentIsNull();
	
	public List<Category> findByParentIsNotNull();
	
	public Page<Category> findByParentIsNotNull(Pageable pageable);
	
	@Query(value = "SELECT * FROM category WHERE parent_id = ?1", 
			countQuery = "SELECT count(*) FROM category WHERE parent_id = ?1", 
			nativeQuery = true)
    public Page<Category> findByMainCategoryId(Integer mainCategoryId, Pageable pageable);
	
	@Query(value = "SELECT * FROM category WHERE name LIKE %?1% AND parent_id IS NOT NULL", 
			countQuery = "SELECT count(*) FROM category WHERE name LIKE %?1% AND parent_id IS NOT NULL", 
			nativeQuery = true)
    public Page<Category> findByKeyword(String keyword, Pageable pageable);
}
