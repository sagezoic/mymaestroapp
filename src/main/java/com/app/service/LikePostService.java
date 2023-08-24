package com.app.service;

import java.util.List;

import com.app.dto.LikePostRequestDTO;
import com.app.dto.LikePostResponseDTO;


public interface LikePostService {

	public void addLike(LikePostRequestDTO dto); 
	
	public void removeLike(Long likePostId);
	
	public List<LikePostResponseDTO> getCountLikesPerPostForUser(Long userId);
	
	public List<LikePostResponseDTO> getCountAllLikesPost();
}
