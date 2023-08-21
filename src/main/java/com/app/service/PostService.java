package com.app.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.app.dto.PostRequestDTO;
import com.app.dto.PostResponseDTO;
import com.app.entities.Post;

public interface PostService {

	PostResponseDTO addPost(PostRequestDTO dto) throws IOException; 
	
	List<Post> getAllPost(Long userId);
	
	void deletePost(Long userId,Long postId);
	
	PostResponseDTO editPost(Long postId,String caption) throws IOException;
}
