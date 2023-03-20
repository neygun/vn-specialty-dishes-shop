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

import com.specialtyshop.entity.Post;
import com.specialtyshop.service.PostService;

@Controller
@RequestMapping("/post")
public class PostController {

	private PostService postService;

	@Autowired
	public PostController(PostService postService) {
		this.postService = postService;
	}
	
	@GetMapping("/list")
    public String listPosts(@RequestParam("page") Optional<Integer> page, Model model) {
    	
		int currentPage = page.orElse(1);
		
		Page<Post> postPage = postService.findPosts("date-desc", currentPage);
		List<Post> posts = postPage.getContent();
        model.addAttribute("posts", posts);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalPages", postPage.getTotalPages());
		
        return "post/list";
    }
	
	@GetMapping("/search")
    public String searchPosts(@RequestParam("keyword") String keyword,
			@RequestParam("page") Optional<Integer> page,
			Model model) {
    	
    	int currentPage = page.orElse(1);
    	
    	Page<Post> postPage = postService.searchPosts(keyword, currentPage);
		List<Post> posts = postPage.getContent();
        model.addAttribute("posts", posts);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalPages", postPage.getTotalPages());
		model.addAttribute("keyword", keyword);
		
        return "post/list";
    }
	
	@GetMapping("/detail/{postId}")
	public String viewDetail(@PathVariable("postId") Integer postId, Model model) {
		
		Post post = postService.findById(postId);
		model.addAttribute("post", post);
		return "post/detail";
	}
}
