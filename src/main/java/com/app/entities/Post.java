package com.app.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="post_table")
@NoArgsConstructor
@Getter
@Setter
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
	
}
