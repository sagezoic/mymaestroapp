package com.app.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Range;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AuthRequest {
	
	//validation annotation
	@NotBlank(message = "Email must be supplied")
	@Email(message = "Invalid Email")
	
	private String email;
	@Pattern(regexp = "((?=.*\\d)(?=.*[a-z])(?=.*[#@$*]).{5,20})", message = "Blank/Invalid password format")
	private String password;
	public int rememberme;
}
