package com.app.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;

import com.app.entities.Servce;
import com.app.entities.ServiceTransaction;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ServiceRequestRequestDTO {
	
	private Long id;
	
	private LocalDateTime slotDate;

	private String description;

	private Long maestroUserId;

	private Long explorerUserId;

	private Long serviceId;

	private boolean status;

	private LocalDate requestGenTime;

	private String transactionId;

}
