package com.app.dto;

import com.app.entities.PostType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostResponseDTO {

	private Long id;
	private PostType postType;
	private String captionText;
	private String urlText;
	private Long userId;
}
