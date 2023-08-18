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
import java.util.Optional;


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

//registering or adding user	
//----------------------------------------------------------------------
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
//-------------------------------------------------------------------------	


//edit user 
//------------------------------------------------
	@Override
	public void editUser(UserDto request)
	{
		Users user=mapper.map(request, Users.class);
		System.out.println(user);
		Users persistentUser=userDao.save(user);
	}
	
//deleting user using email --not working
//-------------------------------------------------------------------
//	@Override
//	public void deleteUser(String Email) {
//		// TODO Auto-generated method stub
//		
//		Optional<Users> user=userDao.findByEmail(Email);
//		
//		if(user.isPresent())
//		{
//			userDao.dele;
//		}
//	}
//---------------------------------------------------------------


	
//deleting user using userId
//----------------------------------------------------------	
	@Override
	public void deleteUser(Long userid) {
		// TODO Auto-generated method stub
		Optional<Users> user=userDao.findById(userid);
		if(user.isPresent())
		{
			userDao.deleteById(userid);
		}
	}
//-------------------------------------------------------	
	
	
	
}
