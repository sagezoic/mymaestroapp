package com.app.service;

import java.util.List;

import com.app.dto.FollowersResponseDTO;



public interface FollowersService {
	
	String addFollowers(Long followersId , Long userId);
	public String addFollowing(Long followingId , Long userId);
	String deleteFollowers(Long followersId,Long userId);
	String deleteFollowing(Long followingId, Long userId);
	
	List<FollowersResponseDTO> getListOfFollwersForSUser(Long userId);

	
}

