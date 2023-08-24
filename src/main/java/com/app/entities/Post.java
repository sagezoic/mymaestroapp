package com.app.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="post_table")
@Getter
@Setter
@ToString
public class Post extends BaseEntity {

	@Enumerated(EnumType.STRING)
	@Column(name="post_type")
	private PostType postType;
	
	@Column(name="caption_text",length=1000)
	private String captionText;
	
	@Column(name="url_text",length=500)
	private String urlText;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private Users userId;
	
	@Column(name="timestamp")
	private LocalDateTime timeStamp;
	
	@OneToOne(mappedBy = "postId",cascade = CascadeType.ALL,orphanRemoval = true)
	private LikePost likePost;
	
	public Post() {
		this.timeStamp = LocalDateTime.now();
	}

	public Post(PostType postType, String captionText, String urlText, Users userId) {
		super();
		this.postType = postType;
		this.captionText = captionText;
		this.urlText = urlText;
		this.userId = userId;
		this.timeStamp = LocalDateTime.now();
	}
	
	
}
