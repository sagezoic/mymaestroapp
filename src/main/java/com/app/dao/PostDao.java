package com.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.Post;

public interface PostDao extends JpaRepository<Post, Long> {

		
	
}
