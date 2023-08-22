package com.app.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name="transaction_table")
public class ServiceTransaction {
	@Id
	@Column(name="id",length = 100)
	private String transactionId;
	
	@Column(name="date_gen_time")
	private LocalDate dateGenTime;
	
	@Column(name="amount")
	private float amount;
	
	@OneToOne
	private Users senderUserId;
	
	@OneToOne
	private Users reciverUserId;

	@Column(name="cash_transaction",length =100)
	private String cashTransaction;
	
	@Column(name="payment_mathod")
	private PaymentMethod paymentMethod;
	
}
