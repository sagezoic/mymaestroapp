package com.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.ReportedPost;

public interface ReportedPostDao extends JpaRepository<ReportedPost, Long>{

	ReportedPost findByPostId(Long postId);
}
