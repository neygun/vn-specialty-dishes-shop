package com.specialtyshop.controller.admin;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.specialtyshop.entity.Post;
import com.specialtyshop.service.PostService;

@Controller
@RequestMapping("/admin/post")
public class AdminPostController {

	private PostService postService;
	
	private ServletContext app;

	@Autowired
	public AdminPostController(PostService postService, ServletContext app) {
		this.postService = postService;
		this.app = app;
	}
	
	@GetMapping("/list")
    public String listPosts(@RequestParam(name="sortBy", required=false) String sortBy,
			@RequestParam("page") Optional<Integer> page,
			Model model) {
    	
		int currentPage = page.orElse(1);
		
		Page<Post> postPage = postService.findPosts(sortBy, currentPage);
		List<Post> posts = postPage.getContent();
        model.addAttribute("posts", posts);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalPages", postPage.getTotalPages());
		
		model.addAttribute("sortBy", sortBy);
		
        return "admin/post-list";
    }

	@GetMapping("/add-form")
    public String showAddPostForm(Model model) {

		model.addAttribute("post", new Post());
        return "admin/post-form";
    }

	@PostMapping("/save")
    public String savePost(@ModelAttribute("post") Post post, 
    		@RequestParam("imageFile") MultipartFile imageFile, 
    		RedirectAttributes redirectAttributes) throws IllegalStateException, IOException {
		
		if (!imageFile.isEmpty()) {
    		post.setImage(imageFile.getOriginalFilename());
			String path = app.getRealPath("/static/images/posts/" + post.getImage());
			imageFile.transferTo(new File(path));
		}
    	postService.save(post);
    	redirectAttributes.addFlashAttribute("message", "Cập nhật thành công.");
        return "redirect:/admin/post/list";
    }

    @GetMapping("/update-form")
    public String showUpdatePostForm(@RequestParam("postId") Integer postId, Model model) {
    	
    	Post post = postService.findById(postId);
        model.addAttribute("post", post);
        return "admin/post-form";
    }

    @GetMapping("/delete")
    public String deletePost(@RequestParam("postId") Integer postId, 
    		RedirectAttributes redirectAttributes) {
    	
    	postService.deleteById(postId);
    	redirectAttributes.addFlashAttribute("message", "Xóa thành công.");
        return "redirect:/admin/post/list";
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
		
        return "admin/post-list";
    }
}
