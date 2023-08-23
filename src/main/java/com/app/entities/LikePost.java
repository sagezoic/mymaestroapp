package com.app.entities;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
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
@Table(name="like_post")
public class LikePost extends BaseEntity {
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private Users userId;
	@OneToOne
	@JoinColumn(name="post_id")
	private Post postId;
}
