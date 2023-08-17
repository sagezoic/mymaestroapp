package com.app.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.UserEntity;
import com.app.entities.Users;

public interface UserDao extends JpaRepository<Users, Long> {
//add a finder to load user by email
	Optional<Users> findByEmail(String email);
}
