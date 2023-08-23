package com.app.service;

import java.util.List;

import com.app.dto.LikePostRequestDTO;


public interface LikeIdService {

	public void addLike(LikePostRequestDTO dto); 
	
	public void removeLike(Long likePostId);
	
	public List<Object[]> getLikePost(Long userId);
}
