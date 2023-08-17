package com.app.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class AuthResponse {
	private String message;
	private String jwt;
	private LocalDateTime timestamp;
	public AuthResponse(String message, String jwt) {
		super();
		this.message = message;
		this.jwt = jwt;
		this.timestamp=LocalDateTime.now();
	}	
}
