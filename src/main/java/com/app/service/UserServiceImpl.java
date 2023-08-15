package com.app.service;

import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.custom_exception.ResourceNotFoundException;
import com.app.dao.UserDao;
import com.app.dto.AuthRequest;
import com.app.dto.AuthResp;
import com.app.dto.SignupRequestDTO;
import com.app.dto.SignupResponseDTO;
import com.app.entities.Users;

@Service
@Transactional
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private ModelMapper mapper;
	
	@Override
	public SignupResponseDTO addUser(SignupRequestDTO signUpRequest) {
		// TODO Auto-generated method stub
		System.out.println("request "+ signUpRequest);
		
		Users persistentUser=userDao.save(mapper.map(signUpRequest,Users.class));
		
		return mapper.map(persistentUser, SignupResponseDTO.class);
	}

	@Override
	public List<Users> getAllUsers() {
		// TODO Auto-generated method stub
		return userDao.findAll();
	}

	@Override
	public AuthResp signInUser( AuthRequest request) {
		// TODO Auto-generated method stub
		Users user=userDao.findByEmailAndPassword(request.getEmail(), request.getPassword())
				.orElseThrow(()->new ResourceNotFoundException("invalid Email or Password "));
		return mapper.map(user, AuthResp.class);
		
	}

	
}
