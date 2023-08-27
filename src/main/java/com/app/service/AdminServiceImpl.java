package com.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.PostDao;
import com.app.dao.ReportedPostDao;
import com.app.dao.ReportedUserDao;
import com.app.dao.UserDao;
import com.app.dto.AdminVerificationforUserResponseDto;
import com.app.dto.ReportedPostDTO;
import com.app.dto.ReportedUserDTO;
import com.app.dto.UserSignupResponseDto;
import com.app.entities.Post;
import com.app.entities.ReportedPost;
import com.app.entities.ReportedUser;
import com.app.entities.Users;

import custom_exception.ResourceNotFoundException;

@Service
@Transactional
public class AdminServiceImpl implements AdminService{

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private ReportedPostDao reportedPostDao;
		
	@Autowired
	private PostDao postDao;
	
	@Autowired
	private ReportedUserDao reportedUserDao;
	
	@Override
	public String addAdminVerification(Long userId) 
	{
		Users user = userDao.findById(userId).orElseThrow(()->new ResourceNotFoundException("user invalid"));
		System.out.println("in adminservice addAdminVerification method with "+user);
		user.setAdminVerified(true);
		userDao.save(user);
		return "success";
	}

	@Override
	public String blockUser(Long userId) 
	{		
		Users user=userDao.findById(userId).orElseThrow(()->new ResourceNotFoundException("invalid user"));
		System.out.println("in adminservice blockUser method with user "+user);
		user.setEnabled(false);
		userDao.save(user);
		return "success";
	}

	@Override
	public List<AdminVerificationforUserResponseDto> getAllUsersList() 
	{		
		List<Users> users=userDao.findAll();
		List<AdminVerificationforUserResponseDto> list=new ArrayList<>();
		for(Users u: users)
		{
			list.add(myMapper(u));
		}
		return list;
	}
	
	@Override
	public String removeReportedPost(Long postId) 
	{	
		ReportedPost reportedPosttochange=reportedPostDao.findByPostId(postId);
		if(reportedPosttochange.getRemovedStatus()==false)
		{
			
		Post reportedPost=postDao.findById(postId).orElseThrow(()->new ResourceNotFoundException("Invalid postId"));
		Users user = userDao.findById(reportedPost.getUserId().getId()).orElseThrow(()->new ResourceNotFoundException("Invalid usertId"));
		postDao.delete(reportedPost);
		user.removePost(reportedPost);
		reportedPosttochange.setRemovedStatus(true);
		return "success";
		}
		else
		{
			return "post already removed";
		}
		
	}
	
	@Override
	public String removeReportedUser(Long userId) {
		Users reportedUser = userDao.findById(userId).orElseThrow(()->new ResourceNotFoundException("Invalid User Id"));
		ReportedUser reportedUserTable = reportedUserDao.findByReportedUserId(reportedUser);
		if(reportedUserTable.getRemovedStatus()==false) {
			userDao.delete(reportedUser);
			Users reportingUser = userDao.findById(reportedUserTable.getReportingUserId()).orElseThrow(()->new ResourceNotFoundException("Invalid User Id"));
			reportedUserTable.setRemovedStatus(true);
			return "Reported User is deleted successfully";
		}else {
			return "User already removed";
		}
		

		
		
	}
	
	@Override
	public List<ReportedPostDTO> getAllReportedPost() {
		
		List<ReportedPost> list=reportedPostDao.findAll();
		List<ReportedPostDTO> reportedPostList=new ArrayList<>();
		for(ReportedPost l:list)
		{
			reportedPostList.add(reportedPostDTOMapper(l));
		}
		return reportedPostList;
	}
	ReportedPostDTO reportedPostDTOMapper(ReportedPost reportedPost)
	{
		ReportedPostDTO reportedPostDTO=new ReportedPostDTO();
		reportedPostDTO.setId(reportedPost.getId());
		reportedPostDTO.setPostId(reportedPost.getPostId());
		reportedPostDTO.setRemovedStatus(reportedPost.getRemovedStatus());
		reportedPostDTO.setReportingUserId(reportedPost.getReportingUserId().getId());
		reportedPostDTO.setDescription(reportedPost.getDescription());
		return reportedPostDTO;
	}
	
	AdminVerificationforUserResponseDto myMapper(Users user)
	{
		AdminVerificationforUserResponseDto adminVerificationforUserResponseDto=new AdminVerificationforUserResponseDto();
		adminVerificationforUserResponseDto.setId(user.getId());
		adminVerificationforUserResponseDto.setUserRole(user.getUserRole());
		adminVerificationforUserResponseDto.setUserName(user.getUserName());
		adminVerificationforUserResponseDto.setEmail(user.getEmail());
		adminVerificationforUserResponseDto.setDob(user.getDob());
		adminVerificationforUserResponseDto.setAdminVerified(user.getAdminVerified());
		adminVerificationforUserResponseDto.setInterest(user.getInterest());
		adminVerificationforUserResponseDto.setSocialMediaLink(user.getSocialMediaLink());
		adminVerificationforUserResponseDto.setEnabled(user.getEnabled());
		adminVerificationforUserResponseDto.setCreatedAt(user.getCreatedAt());
		adminVerificationforUserResponseDto.setDpUrl(user.getDpUrl());
		return adminVerificationforUserResponseDto;
	}

	@Override
	public List<ReportedUserDTO> getAllReportedUser() {
		List<ReportedUser> list=reportedUserDao.findAll();
		List<ReportedUserDTO> reportedUserList=new ArrayList<>();
		for(ReportedUser l:list)
		{
			reportedUserList.add(reportedUserDTOMapper(l));
		}
		return reportedUserList;
	}
	ReportedUserDTO reportedUserDTOMapper(ReportedUser reportedUser)
	{
		ReportedUserDTO reportedUserDTO=new ReportedUserDTO();
		reportedUserDTO.setId(reportedUser.getId());
		reportedUserDTO.setRemovedStatus(reportedUser.getRemovedStatus());
		reportedUserDTO.setReportingUserId(reportedUser.getReportingUserId());
		if(reportedUser.getReportedUserId()!=null)
		reportedUserDTO.setReportedUserId(reportedUser.getReportedUserId().getId());
		reportedUserDTO.setDescription(reportedUser.getDescription());
		return reportedUserDTO;
	}

}
