package com.specialtyshop.controller.admin;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.specialtyshop.entity.Coupon;
import com.specialtyshop.service.CouponService;

@Controller
@RequestMapping("/admin/coupon")
public class AdminCouponController {

	private CouponService couponService;

	@Autowired
	public AdminCouponController(CouponService couponService) {
		this.couponService = couponService;
	}
	
	@GetMapping("/list")
    public String listCoupons(@RequestParam(name="sortBy", required=false) String sortBy,
			@RequestParam("page") Optional<Integer> page,
			Model model) {
    	
		int currentPage = page.orElse(1);
		
		Page<Coupon> couponPage = couponService.findCoupons(sortBy, currentPage);
		List<Coupon> coupons = couponPage.getContent();
        model.addAttribute("coupons", coupons);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalPages", couponPage.getTotalPages());
		
		model.addAttribute("sortBy", sortBy);
		
        return "admin/coupon-list";
    }

	@GetMapping("/add-form")
    public String showAddCouponForm(Model model) {

		model.addAttribute("coupon", new Coupon());
        return "admin/coupon-form";
    }

	@PostMapping("/save")
    public String saveCoupon(@ModelAttribute("coupon") Coupon coupon, 
    		RedirectAttributes redirectAttributes) {
		
		couponService.save(coupon);
    	redirectAttributes.addFlashAttribute("message", "Cập nhật thành công.");
        return "redirect:/admin/coupon/list";
    }

    @GetMapping("/update-form")
    public String showUpdateCouponForm(@RequestParam("couponId") Integer couponId, Model model) {
    	
    	Coupon coupon = couponService.findById(couponId);
        model.addAttribute("coupon", coupon);
        return "admin/coupon-form";
    }

    @GetMapping("/delete")
    public String deleteCoupon(@RequestParam("couponId") Integer couponId, 
    		RedirectAttributes redirectAttributes) {
    	
    	couponService.deleteById(couponId);
    	redirectAttributes.addFlashAttribute("message", "Xóa thành công.");
        return "redirect:/admin/coupon/list";
    }

    @GetMapping("/search")
    public String searchCoupons(@RequestParam("keyword") String keyword,
			@RequestParam("page") Optional<Integer> page,
			Model model) {
    	
    	int currentPage = page.orElse(1);
    	
    	Page<Coupon> couponPage = couponService.searchCoupons(keyword, currentPage);
		List<Coupon> coupons = couponPage.getContent();
        model.addAttribute("coupons", coupons);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalPages", couponPage.getTotalPages());		
		model.addAttribute("keyword", keyword);
		
        return "admin/coupon-list";
    }
}
