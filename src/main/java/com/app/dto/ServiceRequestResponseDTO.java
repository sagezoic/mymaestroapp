package com.app.dto;

import java.time.LocalDate;

import javax.persistence.Entity;

import com.app.entities.ServiceTransaction;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ServiceRequestResponseDTO {
	
	private Long id;
	
	private LocalDate slotDate;

	private String description;

	private Long maestroUserId;

	private Long explorerUserId;

	private Long serviceId;

	private boolean status;

	private LocalDate requestGenTime;

	private ServiceTransaction transactionId;

	
}
