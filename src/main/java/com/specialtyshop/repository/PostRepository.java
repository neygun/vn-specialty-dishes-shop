package com.specialtyshop.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.specialtyshop.entity.Post;

public interface PostRepository extends JpaRepository<Post, Integer> {

	@Query(value = "SELECT * FROM post WHERE title LIKE %?1%", 
			countQuery = "SELECT count(*) FROM post WHERE title LIKE %?1%", 
			nativeQuery = true)
    public Page<Post> findByKeyword(String keyword, Pageable pageable);
}
