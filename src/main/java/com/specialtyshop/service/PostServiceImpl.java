package com.specialtyshop.service;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.specialtyshop.entity.Post;
import com.specialtyshop.repository.PostRepository;

@Service
public class PostServiceImpl implements PostService {

	private PostRepository postRepository;

	@Autowired
	public PostServiceImpl(PostRepository postRepository) {
		this.postRepository = postRepository;
	}

	@Override
	public Post findById(Integer postId) {
		
		Optional<Post> result = postRepository.findById(postId);
		Post post = null;
        if (result.isPresent()) {
        	post = result.get();
        } else {
            throw new RuntimeException("Did not find post id - " + postId);
        }
        return post;
	}

	@Override
	public void save(Post post) {
		
		post.setPostDate(new Date());
		postRepository.save(post);
	}

	@Override
	public void deleteById(Integer postId) {
		postRepository.deleteById(postId);
	}
	
	private Sort getSort(String sortBy) {
		
		Sort sort = null;
		if (sortBy == null || sortBy.equals("default")) {
			sort = Sort.unsorted();
		} else if (sortBy.equals("date-desc")) {
			sort = Sort.by("postDate").descending();
		} else if (sortBy.equals("date-asc")) {
			sort = Sort.by("postDate").ascending();
		}
		return sort;
	}

	@Override
	public Page<Post> findPosts(String sortBy, int page) {
		
		int pageSize = 6;
		Sort sort = getSort(sortBy);
		Pageable pageable = PageRequest.of(page-1, pageSize, sort);
		return postRepository.findAll(pageable);
	}

	@Override
	public Page<Post> searchPosts(String keyword, int page) {
		
		if (keyword != null && keyword.trim().length() > 0) {
			int pageSize = 6;
			Pageable pageable = PageRequest.of(page-1, pageSize);
            return postRepository.findByKeyword(keyword, pageable);
        }
        return findPosts(null, page);
	}
	
	
}
