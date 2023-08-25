package com.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.entities.Post;

public interface PostDao extends JpaRepository<Post, Long> {

	@Query("SELECT p.id, p.captionText,p.postType, p.timeStamp, p.urlText,p.userId FROM Post p")
    List<Object[]> fetchAlllPost();	
	
}

