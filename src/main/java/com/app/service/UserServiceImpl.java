package com.app.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.UserDao;
import com.app.dto.UserDto;
import com.app.dto.UserSignupResponseDto;
//import com.app.entities.UserEntity;
import com.app.entities.Users;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	//dep : user dao
	@Autowired
	private UserDao userDao;
	//dep : password encoder
	@Autowired
	private PasswordEncoder encoder;
	//dep : model mapper
	@Autowired
	private ModelMapper mapper;

	@Override
	public UserSignupResponseDto registerUser(UserDto dto) {
		// map dto --> entity
		Users user=mapper.map(dto,Users.class);
		//encode pwd
		System.out.println(user);
		user.setPassword(encoder.encode(user.getPassword()));
		//save
		Users persistentUser = userDao.save(user);
		// map persistent entity --> dto
		return mapper.map(persistentUser,UserSignupResponseDto.class);
	}
}
