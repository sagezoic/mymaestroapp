package com.app.service;

public interface ReportedPostService {

	String addReportedPost(Long userId,Long postId);
	String removeReportedPost(Long postId);
}
