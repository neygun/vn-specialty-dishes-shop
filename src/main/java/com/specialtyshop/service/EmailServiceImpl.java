package com.specialtyshop.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.specialtyshop.entity.Account;
import com.specialtyshop.entity.VerificationToken;

@Service
public class EmailServiceImpl implements EmailService {

	private VerificationTokenService verificationTokenService;
	
	private JavaMailSender mailSender;
	
	@Autowired
	public EmailServiceImpl(VerificationTokenService verificationTokenService, JavaMailSender mailSender) {
		this.verificationTokenService = verificationTokenService;
		this.mailSender = mailSender;
	}

	@Override
	public void sendMail(Account account) throws MessagingException {
		
		VerificationToken verificationToken = verificationTokenService.findByAccount(account);
		
		// check if the account has a token
		if (verificationToken != null) {
			String token = verificationToken.getToken();
			String verificationUrl = "http://localhost:8080/activation?token=" + token;
			String recipientAddress = account.getEmail();
			String subject = "Xác nhận email của bạn";
			String content = "Vui lòng xác nhận địa chỉ email của bạn để hoàn thành việc tạo tài khoản:<br>" 
					+ "<a href='" + verificationUrl + "'>XÁC NHẬN</a>";
			
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
			helper.setTo(recipientAddress);
			helper.setSubject(subject);
			helper.setText(content, true);
			mailSender.send(message);
		}
	}
}
