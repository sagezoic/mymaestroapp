package com.app.dao;

import java.util.List;
import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import com.app.entities.Post;
//import com.app.entities.UserEntity;
import com.app.entities.Users;

//@EnableJpaRepositories
@Repository
public interface UserDao extends JpaRepository<Users, Long> {
//add a finder to load user by email
	Optional<Users> findByEmail(String email);
	Optional<Users> findById(Users users);

	
	@Query("select u from Users u where u.userRole='ROLE_MAESTRO'")
	List<Object []> getAllMaestroUser();
}
