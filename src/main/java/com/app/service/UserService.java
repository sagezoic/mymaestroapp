package com.app.service;

import com.app.dto.UserDto;
import com.app.dto.UserSignupResponseDto;

public interface UserService {
//add a method for user registration
	UserSignupResponseDto registerUser(UserDto dto);
//----------------------------------------	
//deleting user using userid
	void deleteUser(Long userid);
//------------------------------------------
//deleting user using email
	//void deleteUser(String Email);
//-----------------------------------------------

//edit user
//------------------------------------------------
	void editUser(UserDto request);
//--------------------------------------------------	
}
