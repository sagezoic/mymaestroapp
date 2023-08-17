package com.app.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.UserDao;
//import com.app.entities.UserEntity;
import com.app.entities.Users;

@Service
@Transactional
public class MyUserDetailsService implements UserDetailsService {
	// dependency : user dao i/f
	@Autowired
	private UserDao userDao;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Users user = userDao.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException("Invalid User email !!!!!"));
		// => user exists by specified email
		// user entity(holds data from db ) ---> UserDetails : spring sec class , by
		// creating it's implementation class
		return new MyUserDetails(user);
	}

}
