package com.app.dto;

import java.time.LocalDate;

import com.app.entities.ServiceTransaction;

public class ServiceRequestRequest {
	private LocalDate slotDate;

	private String description;

	private Long maestroUserId;

	private Long explorerUserId;

	private Long serviceId;

	private boolean status;

	private LocalDate requestGenTime;

	private ServiceTransaction transactionId;

}
