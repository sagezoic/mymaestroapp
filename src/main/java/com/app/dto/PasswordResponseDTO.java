package com.app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor

public class PasswordResponseDTO {
	private String email;
	private String password;
	public PasswordResponseDTO(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}	
	
	
}

