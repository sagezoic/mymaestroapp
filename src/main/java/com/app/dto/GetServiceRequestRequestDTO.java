package com.app.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.app.entities.ServiceType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetServiceRequestRequestDTO {
	
	private Long id;
	
	private LocalDateTime slotDate;

	private String description;

	private Long maestroUserId;

	private Long explorerUserId;

	private Long serviceId;

	private boolean status;

	private LocalDateTime requestGenTime;

	private String transactionId;
	
	private String firstName;
	
	private String lastName;
	
	private String serviceTitle;
	
	private ServiceType serviceType;
	
	private Float amount;

}
