package com.app.service;

import java.util.List;

import com.app.dto.AdminVerificationforUserResponseDto;
import com.app.dto.ReportedPostDTO;
import com.app.entities.Users;

public interface AdminService {

	String addAdminVerification(Long userId);
	String blockUser(Long userId);
	List<AdminVerificationforUserResponseDto> getAllUsersList();
	String removeReportedPost(Long postId);
	List<ReportedPostDTO> getAllReportedPost();
}
