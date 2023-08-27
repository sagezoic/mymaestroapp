package com.app.service;

import com.app.dto.ReportedPostDTO;

public interface ReportedPostService {

	String addReportedPost(ReportedPostDTO dto);
	String removeReportedPost(Long postId);
}
