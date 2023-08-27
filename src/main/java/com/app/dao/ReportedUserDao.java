package com.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.ReportedUser;
import com.app.entities.Users;

public interface ReportedUserDao extends JpaRepository<ReportedUser, Long> {
	
	ReportedUser findByReportedUserId(Users user);
}