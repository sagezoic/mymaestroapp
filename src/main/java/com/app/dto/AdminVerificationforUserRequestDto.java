package com.app.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.app.entities.UserInterest;
import com.app.entities.UserRole;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdminVerificationforUserRequestDto {

	private Long id;
	
	private String firstName;
	
	private String lastName;
	
	private String email;
	
	private String userName;
	
	private String password;
	
	private UserRole userRole;
	
	private LocalDate dob;
	
	private LocalDateTime createdAt;

	private boolean otpVerified;

	private String dpUrl;
	
	private UserInterest interest;
	
	private String bio;
	
	private String socialMediaLink;
	
	private int token;
	
	private boolean enabled;

	private boolean adminVerified;

	
}
