package com.app.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name="service_request")
@NoArgsConstructor
public class ServiceRequest extends BaseEntity {
	
	@Column(name="slot_date")
	private LocalDate slotDate;
	
	@Column(name="description",length=1000)
	private String description;
	
	@Column(name="maestro_user_id")
	private Long maestroUserId;
	
	@Column(name="explorer_user_id")
	private Long explorerUserId;
	
	@ManyToOne
	@JoinColumn(name="service_id")
	private Servce serviceId;
	
	@Column(name="status")
	private boolean status;
	
	@Column(name="req_gen_time")
	private LocalDateTime requestGenTime;
	
	@OneToOne(mappedBy = "serviceRequestId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private ServiceTransaction transactionId;
	

	@Override
	public String toString() {
		return "ServiceRequest [slotDate=" + slotDate + ", description=" + description + ", maestroUserId="
				+ maestroUserId + ", explorerUserId=" + explorerUserId + ", status=" + status + ", requestGenTime="
				+ requestGenTime + "]";
	}


	public boolean getStatus() {
		// TODO Auto-generated method stub
		return status;
	}
}
