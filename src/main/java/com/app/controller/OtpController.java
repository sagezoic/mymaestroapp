//package com.app.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import com.app.dto.EmailRequestDTO;
//import com.app.dto.EmailResponseDTO;
//import com.app.dto.VerifyOTPRequestDTO;
//import com.app.dto.VerifyOTPResponseDTO;
//import com.app.service.OTPService;
//
//@RestController
//@RequestMapping("/api")
//public class OtpController {
//	
//	@Autowired
//    private OTPService otpService;
//	
//	String storedOtp;
//    
//    public OtpController(OTPService otpService) {
//        this.otpService = otpService;
//    }
//
//    @PostMapping("/send-otp")
//    public ResponseEntity<EmailResponseDTO> sendOTP(@RequestBody EmailRequestDTO emailRequest) {
//        String email = emailRequest.getEmail();
//
//        // Generate OTP
//        String otp = otpService.generateOTP();
//
//        // Store OTP in cache with the email
//        otpService.storeOTPInBackend(email, otp);
//
//        // Send OTP to the user's email
//        otpService.sendOTPByEmail(email, otp);
//
//        String responseMessage = "OTP sent successfully!";
//        EmailResponseDTO responseDTO = new EmailResponseDTO(email, responseMessage);
//
//        return ResponseEntity.ok(responseDTO);
//    }
//    
//    
//    @PostMapping("/verify-otp")
//    public ResponseEntity<VerifyOTPResponseDTO> verifyOTP(@RequestBody VerifyOTPRequestDTO request) {
//        
//    	String email = request.getEmail();
//        String enteredOTP = request.getEnteredOTP();
//
//        VerifyOTPResponseDTO response = otpService.verifyOTP(email, enteredOTP);
//
//        return ResponseEntity.ok(response);
//    }
//    
//    
//}