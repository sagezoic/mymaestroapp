package com.app.service;

import java.util.List;

import javax.validation.Valid;

import com.app.dto.AuthRequest;
import com.app.dto.AuthResp;
import com.app.dto.SignupRequestDTO;
import com.app.dto.SignupResponseDTO;
import com.app.entities.Users;

public interface UserService {

	SignupResponseDTO addUser(SignupRequestDTO signUpRequest);
	
	List<Users> getAllUsers();

	AuthResp signInUser(@Valid AuthRequest request);
}
