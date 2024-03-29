package com.app.entities;

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
@Getter
@Setter
@ToString@NoArgsConstructor
@AllArgsConstructor
public class Followers extends BaseEntity {
	
	@Column(name="user_id")
	private Long userId;

	@ManyToOne
	@JoinColumn(name="followersId")
	private Users followersId;
}

