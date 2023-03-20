package com.specialtyshop.service;

import javax.mail.MessagingException;

import com.specialtyshop.entity.Account;

public interface EmailService {

	public void sendMail(Account account) throws MessagingException;
}
