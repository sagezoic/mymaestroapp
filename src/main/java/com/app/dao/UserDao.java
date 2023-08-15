package com.app.dao;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.Users;

public interface UserDao extends JpaRepository<Users, Long>{

	Optional<Users> findByEmailAndPassword(String em,String pass);
	Optional<Users> findByEmail(String em);
}
