package com.app.dto;


import java.time.LocalDate;


import com.app.entities.UserInterest;
import com.app.entities.UserRole;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

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
public class UserDto {
	//skip id during de-ser
	//@JsonProperty(access = Access.WRITE_ONLY)
	private Long id;
	private String firstName;
	private String lastName;
	private String email;
	private String userName;

	//skip password during ser.
	//@JsonIgnore
	//@JsonProperty(access = Access.WRITE_ONLY)
	private String password;
	private UserRole userRole;
	private LocalDate dob;

	private boolean otpVerified;

	private String dpUrl;
	private UserInterest interest;
	private String bio;
	private String socialMediaLink;
	private int token;
	private boolean enabled;



	public UserDto(String firstName, String lastName, String email, String password, UserRole userRole,LocalDate dob,UserInterest interest) {
		super();
		
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.userRole = userRole;
		this.dob = dob;
		this.interest = interest;
				
	}
	
}
