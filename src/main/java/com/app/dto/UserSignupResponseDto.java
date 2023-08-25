package com.app.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.app.entities.UserInterest;
import com.app.entities.UserRole;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserSignupResponseDto {
	
	//private Long id;
	private String firstName;
	private String lastName;
	private String email;
	private UserRole userRole;
	private UserInterest interest;
	private LocalDate dob;
	private LocalDateTime createdAt;
	private String userName;
	private String dpUrl;
	
	

	public UserSignupResponseDto(/*Long id,*/String firstName, String lastName, String email, UserRole userRole,UserInterest interest,LocalDate dob,String userName,String dpUrl) {
		super();
		//this.id=id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.userRole = userRole;
		this.interest=interest;
		this.dob=dob;
		this.userName=userName;
		this.dpUrl=dpUrl;
		this.createdAt=LocalDateTime.now();
	}	
}
