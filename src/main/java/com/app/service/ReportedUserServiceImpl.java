package com.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.ReportedPostDao;
import com.app.dao.ReportedUserDao;
import com.app.dao.UserDao;
import com.app.dto.ReportedUserDTO;
import com.app.dto.ServiceRequestRequestDTO;
import com.app.dto.ServiceRequestResponseDTO;
import com.app.entities.ReportedPost;
import com.app.entities.ReportedUser;
import com.app.entities.Users;

import custom_exception.ResourceNotFoundException;
@Service
@Transactional
public class ReportedUserServiceImpl implements ReportedUserService {

	@Autowired
	private ReportedUserDao reportedUserDao;
	
	@Autowired
	private UserDao userDao;
@Override
public String addReportedUser(ReportedUserDTO dto) {
	ReportedUser reportedUser = new ReportedUser();
	reportedUser.setId(dto.getId());
	reportedUser.setRemovedStatus(false);
	reportedUser.setDescription(dto.getDescription());
	reportedUser.setReportedUserId(userDao.findById(dto.getReportedUserId()).orElseThrow(()->new ResourceNotFoundException("invalid userId")));
	reportedUser.setReportingUserId(dto.getReportingUserId());
	ReportedUser persistance=reportedUserDao.save(reportedUser);
	Users user = userDao.findById(persistance.getReportingUserId()).orElseThrow(()->new ResourceNotFoundException("invalid userId"));
	user.addReportedUser(persistance);
	return "sucess" ;
}

	
}
