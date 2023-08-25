package com.app.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="transaction_table")
public class ServiceTransaction {
	@Id
	@Column(name="id",length = 100)
	private String id;
	
	@Column(name="date_gen_time")
	private LocalDateTime dateGenTime;
	
	@Column(name="amount")
	private float amount;
	
	@Enumerated(EnumType.STRING)
	@Column(name="payment_method",length=30)
	private PaymentMethod paymentMethod;
	
	@JoinColumn(name="sender_user_id")
	@OneToOne
	private Users senderUserId;
	
	@JoinColumn(name="reciver_user_id")
	@OneToOne
	private Users reciverUserId;
	
	@JoinColumn(name="service_id")
	@ManyToOne
	private Servce serviceId;
	
	@OneToOne
	@JoinColumn(name="service_request_id")
	private ServiceRequest serviceRequestId;
	
}
