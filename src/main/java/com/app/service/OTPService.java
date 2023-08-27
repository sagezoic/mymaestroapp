package com.app.service;

import com.app.dto.VerifyOTPResponseDTO;

public interface OTPService {
   
	String generateOTP();
    void sendOTPByEmail(String email, String otp);
	void storeOTPInBackend(String email, String otp);
	VerifyOTPResponseDTO verifyOTP(String email, String enteredOTP);
}