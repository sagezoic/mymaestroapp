package com.app.dto;

import org.springframework.web.multipart.MultipartFile;

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
public class PostRequestDTO {
	private Long id;
	private PostType postType;
	private String captionText;
	private String urlText;
	private Long userId;
	private MultipartFile imageFile;

}
