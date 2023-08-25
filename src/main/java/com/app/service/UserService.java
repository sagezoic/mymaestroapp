package com.app.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.app.dto.CommonResponse;
import com.app.dto.ServiceTransactionResponseDTO;
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
	
	byte[] downloadProfileImage(String path) throws IOException;

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

	//get trasaction list of maestro 
	List<ServiceTransactionResponseDTO> getTransactionListOfMaestro(Long userId);
	
	//get trasaction list of explorer 
	List<ServiceTransactionResponseDTO> getTransactionListOfExplorer(Long userId);
	
	UserSignupResponseDto getUserDetailsfromUserId(Long userId);
	
}
