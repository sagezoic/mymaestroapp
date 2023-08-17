package com.app.service;

import com.app.dto.UserDto;
import com.app.dto.UserSignupResponseDto;

public interface UserService {
//add a method for user registration
	UserSignupResponseDto registerUser(UserDto dto);
}
