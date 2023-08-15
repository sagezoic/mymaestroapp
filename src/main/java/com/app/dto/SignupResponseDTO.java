package com.app.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import com.app.entities.UserInterest;
import com.app.entities.UserRole;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SignupResponseDTO {
	private UserRole userRole;
	private String email;
	private String firstName;
	private String lastName;
	private Date dOB;
	private boolean otpVerified;
	private boolean adminVerified;
	private String dpUrl;
	private UserInterest interest;
	private String bio;
	private String socialMediaLink;
	private int token;
	private boolean enabled;
	private Date createdAt;
	
}
