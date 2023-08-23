package com.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.PathVariable;


import com.app.entities.LikePost;

public interface LikePostDao extends JpaRepository<LikePost, Long> {

//	@Query("SELECT COUNT(l) FROM LikePost l where l.getPostId =   and l.getUserId = ")
//    Long getCountOfLikePost();
	
	@Query("SELECT l.postId, COUNT(l) FROM LikePost l WHERE l.userId = :userId GROUP BY l.postId")
    List<Object[]> countLikesPerPostForUser(@PathVariable Long userId);
    
    
}
