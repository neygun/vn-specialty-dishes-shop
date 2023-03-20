package com.specialtyshop.service;

import java.sql.Timestamp;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.specialtyshop.entity.Account;
import com.specialtyshop.entity.VerificationToken;
import com.specialtyshop.repository.VerificationTokenRepository;

@Service
public class VerificationTokenServiceImpl implements VerificationTokenService {

	@Autowired
	private VerificationTokenRepository verificationTokenRepository;
	
	@Override
	public VerificationToken findByToken(String token) {
		return verificationTokenRepository.findByToken(token);
	}
	
	@Override
	public VerificationToken findByAccount(Account account) {
		return verificationTokenRepository.findByAccount(account);
	}

	@Override
	public void save(Account account, String token) {
		
		VerificationToken verificationToken = new VerificationToken(token, account);
		// set expiry date to 24 hours
		verificationToken.setExpiryDate(calculateExpiryDate(24*60));
		verificationTokenRepository.save(verificationToken);
	}
	
	private Timestamp calculateExpiryDate(int expiryTimeInMinutes) {
		
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MINUTE, expiryTimeInMinutes);
        return new Timestamp(cal.getTime().getTime());
    }
}
