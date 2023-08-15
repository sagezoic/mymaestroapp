package com.app.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.app.entities.UserInterest;
import com.app.entities.UserRole;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SignupRequestDTO {
//	@NotBlank(message="role must be supplied")
	private UserRole userRole;
	@NotBlank(message="email must be supplied")
	@Email(message="Invalid Email")
	private String email;
	@NotBlank(message="password must be supplied")
	@Pattern(regexp = "((?=.*\\d)(?=.*[a-z])(?=.*[#@$*]).{5,20})", message = "Blank/Invalid password format")
	private String password;
	@NotBlank(message="first name must be supplied")
	private String firstName;
	@NotBlank(message="last name must be supplied")
	private String lastName;
//	@NotBlank(message="date of birth must be supplied")
	private Date dOB;
	private boolean otpVerified;
	private boolean adminVerified;
//	@Column(length=100,name="dp_url")
	private String dpUrl;
//	@Enumerated(EnumType.STRING)
//	@Column(length=30)
	private UserInterest interest;
//	@Column(length=30)
	private String bio;
//	@Column(name="social_media_link",length=100)
	private String socialMediaLink;
//	@Column(name="token")
	private int token;
//	@Column(name="enabled")
	private boolean enabled;
//	@Column(name="createdAt")
	private Date createdAt;

	
}
