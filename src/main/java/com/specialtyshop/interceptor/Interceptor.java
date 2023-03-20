package com.specialtyshop.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.specialtyshop.entity.Category;
import com.specialtyshop.repository.CategoryRepository;

@Component
public class Interceptor implements HandlerInterceptor {

	private CategoryRepository categoryRepository;
	
	@Autowired
	public Interceptor(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

//		List<Category> mainCategories = categoryRepository.findByParentIsNull();
//		modelAndView.addObject("mainCategories", mainCategories);
	}
}
