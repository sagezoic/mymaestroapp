package com.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.Following;

public interface FollowingDao extends JpaRepository<Following, Long>  {

	List<Following> findByUserId(Long userId);
	List<Following> findByFollowingId(Long followingId);
}
