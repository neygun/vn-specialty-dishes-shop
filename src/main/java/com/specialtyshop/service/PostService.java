package com.specialtyshop.service;

import org.springframework.data.domain.Page;

import com.specialtyshop.entity.Post;

public interface PostService {

	public Post findById(Integer postId);
	
	public void save(Post post);
	
	public void deleteById(Integer postId);
	
	public Page<Post> findPosts(String sortBy, int page);
	
	public Page<Post> searchPosts(String keyword, int page);
}
