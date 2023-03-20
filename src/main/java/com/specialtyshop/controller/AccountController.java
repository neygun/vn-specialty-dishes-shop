package com.specialtyshop.controller;

import java.security.Principal;
import java.sql.Timestamp;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.specialtyshop.entity.Account;
import com.specialtyshop.entity.VerificationToken;
import com.specialtyshop.service.AccountService;
import com.specialtyshop.service.VerificationTokenService;

@Controller
public class AccountController {

	private AccountService accountService;
		
	private VerificationTokenService verificationTokenService;

	@Autowired
	public AccountController(AccountService accountService, VerificationTokenService verificationTokenService) {
		this.accountService = accountService;
		this.verificationTokenService = verificationTokenService;
	}
	
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}
	
	@GetMapping("/login")
	public String showLoginForm(Principal principal) {
		return principal == null ? "account/login" : "redirect:/";
	}

	@GetMapping("/register")
	public String showRegistrationForm(Model model) {
		
		Account account = new Account();
		model.addAttribute("account", account);
		return "account/registration";
	}

	@PostMapping("/register")
	public String processRegistration(@Valid @ModelAttribute("account") Account account, 
			BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		
		// check if the username exists
		if (accountService.usernameExists(account.getUsername())) {
			bindingResult.addError(new FieldError("account", "username", "Tên đăng nhập đã được sử dụng"));
		}
		
		// check if the password matches
		if (account.getPassword() != null && account.getMatchingPassword() != null) {
			if (!account.getPassword().equals(account.getMatchingPassword())) {
				bindingResult.addError(new FieldError("account", "matchingPassword", "Mật khẩu không khớp"));
			}
		}
		
		if (bindingResult.hasErrors()) {
			return "account/registration";
		}
		accountService.register(account);
		redirectAttributes.addFlashAttribute("message", "Thành công! Một email xác nhận đã được gửi đến địa chỉ email của bạn.");
		return "redirect:/login";
	}
	
	@GetMapping("/activation")
	public String activateAccount(@RequestParam("token") String token, Model model) {
		
		VerificationToken verificationToken = verificationTokenService.findByToken(token);
		if (verificationToken == null) {
			model.addAttribute("message", "Verification token không hợp lệ.");
		} else {
			Account account = verificationToken.getAccount();
			
			// if the account is not activated
			if (!account.isEnabled()) {
				Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
				// check if the token is expired
				if (verificationToken.getExpiryDate().before(currentTimestamp)) {
					model.addAttribute("message", "Verification token đã hết hạn.");
				} else {
					// the token is valid
					account.setEnabled(true);
					accountService.save(account);
					model.addAttribute("message", "Tài khoản của bạn đã được kích hoạt.");
				}
			} else {
				// the account is already activated
				model.addAttribute("message", "Tài khoản của bạn đã được kích hoạt rồi.");
			}
		}
		
		return "account/activation";
	}
	
	@GetMapping("/access-denied")
	public String showAccessDeniedPage() {
		return "account/access-denied";
	}
}
