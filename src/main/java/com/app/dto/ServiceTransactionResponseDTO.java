package com.app.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.app.entities.PaymentMethod;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ServiceTransactionResponseDTO {

	private String id;
	
	private LocalDateTime dateGenTime;
	
	private float serviceAmount;
	
	private boolean success;

	private Long senderUserId;
		
	private Long reciverUserId;
	
	private PaymentMethod paymentMethod;
	
	private Long serviceId;
}
