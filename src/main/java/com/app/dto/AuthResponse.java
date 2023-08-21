package com.app.dto;

import java.time.LocalDateTime;

import com.app.entities.UserRole;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class AuthResponse {
	private Long id;
	private String message;
	private String jwt;
	private LocalDateTime timestamp;
	private UserRole userRole;
	public AuthResponse(String message, String jwt,Long id,UserRole userRole) {
		super();
		this.id=id;
		this.message = message;
		this.jwt = jwt;
		this.timestamp=LocalDateTime.now();
		this.userRole=userRole;
	}	
}
