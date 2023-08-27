package com.app.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="reported_user_table")
@NoArgsConstructor
@Getter
@Setter
@ToString
@AllArgsConstructor
public class ReportedUser extends BaseEntity{
	
	
	
	@Column(name="reporting_user_id")
	private Long reportingUserId;
	
	@ManyToOne
	@JoinColumn(name="reported_user_id")
	private Users reportedUserId;
	
	@Column(name="description",length = 2000)
	private String description;
	
	@Column(name="removed_status")
	private boolean removedStatus;

	public boolean getRemovedStatus() {
		return removedStatus;
	}

	
	
	
}
