package com.app.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
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
	private LocalDate dateGenTime;
	
	@Column(name="amount")
	private float amount;
	
	@Enumerated(EnumType.STRING)
	@Column(name="payment_method",length=30)
	private PaymentMethod paymentMethod;
	
	@OneToOne
	private Users senderUserId;
	
	@OneToOne
	private Users reciverUserId;
	
	@ManyToOne
	private Servce serviceId;
	
}
