package com.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.Users;

public interface AdminVerificationforUserRequestDao extends JpaRepository<Users, Long > {

}
