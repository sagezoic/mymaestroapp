package com.app.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.app.entities.UserInterest;
import com.app.entities.UserRole;

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
public class AdminVerificationforUserResponseDto {

	private long id;
	private UserRole userRole;
	private String userName;
	private String email;
	private LocalDate dob;
	private boolean adminVerified;
	private UserInterest interest;
	private String socialMediaLink;
	private boolean enabled;
	private LocalDateTime createdAt;
	
}
