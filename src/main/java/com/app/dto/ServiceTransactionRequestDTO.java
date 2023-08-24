package com.app.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import com.app.entities.PaymentMethod;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor

public class ServiceTransactionRequestDTO {

	private String id;
	
	private LocalDateTime dateGenTime;
	
	private float amount;
	
	private boolean success;

	private Long senderUserId;
	
	private Long reciverUserId;
	
	private PaymentMethod paymentMethod;

	private Long serviceId;

	public ServiceTransactionRequestDTO(float amount,
			boolean success, Long senderUserId, Long reciverUserId,Long serviceId,
			PaymentMethod paymentMethod) {
		super();
		this.id = generateTransactionId(); 
		this.dateGenTime = LocalDateTime.now();
		this.amount = amount;
		this.success = success;
		this.senderUserId = senderUserId;
		this.reciverUserId = reciverUserId;
		this.serviceId=serviceId;
		this.paymentMethod = paymentMethod;
	}
	
	public String generateTransactionId() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
	
}
