package com.app.dto;

import java.time.LocalDate;

import com.app.entities.UserInterest;
import com.app.entities.UserRole;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserSignupResponseDto {
	
	//private Long id;
	private String firstName;
	private String lastName;
	private String email;
	private UserRole userRole;
	private UserInterest interest;
	private LocalDate dob;
	
	

	public UserSignupResponseDto(/*Long id,*/String firstName, String lastName, String email, UserRole userRole,UserInterest interest,LocalDate dob) {
		super();
		//this.id=id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.userRole = userRole;
		this.interest=interest;
		this.dob=dob;
	}	
}
