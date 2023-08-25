package com.app.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="reported_post_table")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ReportedPost extends BaseEntity{
	
	@Column(name="post_id")
	private Long postId;
	
	@Column(name="removed_status")
	private boolean removedStatus;
	
	@ManyToOne
	@JoinColumn(name = "reporting_user_id")
	private Users reportingUserId;

	public boolean getRemovedStatus()
	{
		return this.removedStatus;
	}
	
}
