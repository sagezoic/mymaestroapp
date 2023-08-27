package com.app.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.ReportedPostDao;
import com.app.dao.UserDao;
import com.app.dto.ReportedPostDTO;
import com.app.entities.ReportedPost;

import custom_exception.ResourceNotFoundException;

@Service
@Transactional
public class ReportedPostServiceImpl implements ReportedPostService{

	@Autowired
	private ReportedPostDao reportedPostDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private ModelMapper modelmapper;
	
	@Override
	public String addReportedPost(ReportedPostDTO dto) {
		
		ReportedPost reportedPost=new ReportedPost();
		reportedPost.setPostId(dto.getPostId());
		reportedPost.setRemovedStatus(false);
		reportedPost.setReportingUserId(userDao.findById(dto.getReportingUserId()).orElseThrow(()->new ResourceNotFoundException("invalid userId")));
		reportedPost.setDescription(dto.getDescription());
		ReportedPost persistentReportedPost= reportedPostDao.save(reportedPost);
		
		return "success";
	}

	@Override
	public String removeReportedPost(Long postId) {
		
		ReportedPost reportedPost=reportedPostDao.findById(postId).orElseThrow(()->new ResourceNotFoundException("invalid postId"));
		reportedPostDao.delete(reportedPost);
		return "success";
	}

}
