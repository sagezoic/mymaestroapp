package com.app.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AvailableDate extends BaseEntity{
	
	@Column(name="first_date")
	private LocalDateTime firstDate;
	@Column(name="second_date")
	private LocalDateTime secondDate;
	@Column(name="third_date")
	private LocalDateTime thirdDate;
	@Column(name="fourth_date")
	private LocalDateTime fourthDate;
	@Column(name="fifth_date")
	private LocalDateTime fifthDate;
	@Column(name="sixth_date")
	private LocalDateTime sixthDate;
	@Column(name="seventh_date")
	private LocalDateTime seventhDate;
	
	@ManyToOne
	@JoinColumn(name="service_id")
	  private Servce serviceId;
	  
	
}
