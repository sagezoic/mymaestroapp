package com.app.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.app.dto.CommonResponse;
import com.app.dto.UserDto;
import com.app.dto.UserSignupResponseDto;

public interface UserService {
//add a method for user registration
	UserSignupResponseDto registerUser(UserDto dto);
	
	UserSignupResponseDto addRoleDetails(UserDto dto);
	
	UserSignupResponseDto addBasicDetails(UserDto dto);
	
	UserSignupResponseDto addInterest(UserDto dto);
	
	UserSignupResponseDto uploadProfileImage(Long userId, MultipartFile file) throws IOException;
	
	byte[] downloadProfileImage(Long empId) throws IOException;
}
