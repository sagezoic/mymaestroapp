package com.app.service;

import java.io.File;
import java.io.IOException;

import javax.annotation.PostConstruct;

import org.apache.commons.io.FileUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.app.dao.UserDao;
import com.app.dto.CommonResponse;
import com.app.dto.UserDto;
import com.app.dto.UserSignupResponseDto;
//import com.app.entities.UserEntity;
import com.app.entities.Users;

import custom_exception.ResourceNotFoundException;

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
	
	//declare in application.properties
	@Value("${upload.location}")
	private String folderLocation;

	private Users findByEmail(UserDto dto) {
		return userDao.findByEmail(dto.getEmail())
				.orElseThrow(()->new ResourceNotFoundException("Email is Not Vaild"));   
	
	}
	
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
	
	
	@Override
	public UserSignupResponseDto addRoleDetails(UserDto dto) {
		//user find by email
		Users user = findByEmail(dto);
		//set the role
		user.setUserRole(dto.getUserRole());
		//save in database
		Users persistenceuser = userDao.save(user); 
		
		return mapper.map(persistenceuser, UserSignupResponseDto.class);
	}
	
	@Override
	public UserSignupResponseDto addBasicDetails(UserDto dto) {
		Users user = findByEmail(dto);
		user.setFirstName(dto.getFirstName());
		user.setLastName(dto.getLastName());
		user.setDob(dto.getDob());
		Users persistenceuser = userDao.save(user);
		return mapper.map(persistenceuser, UserSignupResponseDto.class);
	}
	
		@Override
		public UserSignupResponseDto addInterest(UserDto dto) {
			Users user = findByEmail(dto);
			user.setInterest(dto.getInterest());
			Users persistenceuser = userDao.save(user);
			return mapper.map(persistenceuser, UserSignupResponseDto.class);
		}
	
		
		@PostConstruct
		public void init() {
			System.out.println("in init " + folderLocation);
			// chk if folder exists
			File folder = new File(folderLocation);
			if (folder.exists())
				System.out.println("folder alrdy exists !");
			else {
				folder.mkdir(); // creates a new folder
				System.out.println("created a new folder...");
			}

		}
		
		
		
		@Override
		public UserSignupResponseDto uploadProfileImage(Long userId, MultipartFile file) throws IOException {
			Users user = userDao.findById(userId).orElseThrow(()->new ResourceNotFoundException("User is not valid"));
			String path = folderLocation.concat(user.getUserRole().toString()).concat("/").concat(user.getUserName()).concat("/").concat(file.getOriginalFilename());
			System.out.println("Path"+path);
			
			FileUtils.writeByteArrayToFile(new File(path), file.getBytes());
			user.setDpUrl(path);
			return mapper.map(user,UserSignupResponseDto.class);
		}
		
		@Override
		public byte[] downloadProfileImage(Long empId) throws IOException {
			Users user = userDao.findById(empId).orElseThrow(()-> new ResourceNotFoundException("User is not valid"));
			if(user.getDpUrl()!=null) {
				return FileUtils.readFileToByteArray(new File(user.getDpUrl()));
			}
			
			throw new ResourceNotFoundException("Image not yet assigned");
		}
}
