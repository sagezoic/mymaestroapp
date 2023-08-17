package com.app.dto;

import com.app.entities.UserRole;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@NoArgsConstructor
public class UserDto {
	//skip id during de-ser
	//@JsonProperty(access = Access.WRITE_ONLY)
	private Long id;
	private String firstName;
	private String lastName;
	private String email;
	//skip password during ser.
	//@JsonIgnore
	//@JsonProperty(access = Access.WRITE_ONLY)
	private String password;
	private UserRole userRole;

	public UserDto(String firstName, String lastName, String email, String password, UserRole userRole) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.userRole = userRole;
	}
	
}
