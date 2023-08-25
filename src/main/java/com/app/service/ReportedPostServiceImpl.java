package com.app.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.ReportedPostDao;
import com.app.dao.UserDao;
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
	public String addReportedPost(Long userId, Long postId) {
		
		ReportedPost reportedPost=new ReportedPost();
		reportedPost.setPostId(postId);
		reportedPost.setRemovedStatus(false);
		reportedPost.setReportingUserId(userDao.findById(userId).orElseThrow(()->new ResourceNotFoundException("invalid userId")));
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
