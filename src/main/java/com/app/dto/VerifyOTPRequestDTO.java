package com.app.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class VerifyOTPRequestDTO {

	 private String email;
	    
	    private String otp;
	    
	    private LocalDateTime timestamp;
	    
	    private String enteredOTP;

	
}
