package com.app.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.app.dto.PostRequestDTO;
import com.app.dto.PostResponseDTO;

public interface PostService {

	PostResponseDTO addPost(Long userId , MultipartFile file,PostRequestDTO dto) throws IOException; 
}