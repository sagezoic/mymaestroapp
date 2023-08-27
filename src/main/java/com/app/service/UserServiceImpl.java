package com.app.service;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

import javax.annotation.PostConstruct;

import org.apache.commons.io.FileUtils;
import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.web.multipart.MultipartFile;

import com.app.dao.FollowersDao;
import com.app.dao.FollowingDao;
import com.app.dao.UserDao;
import com.app.dto.CommonResponse;
import com.app.dto.PasswordRequestDTO;
import com.app.dto.PasswordResponseDTO;
import com.app.dto.ServiceTransactionResponseDTO;
import com.app.dto.UserDto;
import com.app.dto.UserSignupResponseDto;
import com.app.entities.Followers;
import com.app.entities.Following;
import com.app.entities.ServiceTransaction;
//import com.app.entities.UserEntity;
import com.app.entities.Users;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


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
	
	@Autowired
	private FollowingDao followingDao;
	
	@Autowired
	private FollowersDao followersDao;
	
	//declare in application.properties
	@Value("${upload.locationmaestro}")
	private String maestroImageLocation;
	
	@Value("${upload.locationexplorer}")
	private String explorerImageLocation;

	private Users findByEmail(UserDto dto) {
		return userDao.findByEmail(dto.getEmail())
				.orElseThrow(()->new ResourceNotFoundException("Email is Not Vaild"));   
	
	}
	
//registering or adding user	
//----------------------------------------------------------------------

	@Override
	public UserSignupResponseDto registerUser(UserDto dto) {
		// map dto --> entity
		Users user=mapper.map(dto,Users.class);
		//encode pwd
		System.out.println(user);
		user.setPassword(encoder.encode(user.getPassword()));
		user.setCreatedAt(LocalDateTime.now());
		Users persistentUser = userDao.save(user);
		Followers followers = new Followers();
		followers.setUserId(persistentUser.getId());
		followersDao.save(followers);
		Following following = new Following();
		following.setUserId(persistentUser.getId());
		followingDao.save(following);
		
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
		user.setUserName(dto.getUserName());
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
			System.out.println("in init " + maestroImageLocation +" "+ explorerImageLocation);
			// chk if folder exists
			File maestro = new File(maestroImageLocation);
			if (maestro.exists())
				System.out.println("folder alrdy exists !");
			else {
				maestro.mkdir(); // creates a new folder
				System.out.println("created a new folder...");
			}
			
			File explorer = new File(explorerImageLocation);
			if (explorer.exists())
				System.out.println("folder alrdy exists !");
			else {
				explorer.mkdir(); // creates a new folder
				System.out.println("created a new folder...");
			}

		}
		
		
		
		@Override
		public UserSignupResponseDto uploadProfileImage(Long userId, MultipartFile file) throws IOException {
			Users user = userDao.findById(userId).orElseThrow(()->new ResourceNotFoundException("User is not valid"));
			//String path = folderLocation.concat(user.getUserRole().toString()).concat("/").concat(user.getUserName())
				//	.concat("/").concat(file.getOriginalFilename());
			String path=null;
			if(user.getUserRole().toString()=="ROLE_MAESTRO")
			{
				path =maestroImageLocation.concat(user.getUserName());
				path=path.concat("/profile/");
				path=path.concat(file.getOriginalFilename());
				System.out.println("Path"+path);
					
			}
			else if(user.getUserRole().toString()=="ROLE_EXPLORER")
			{
				path =explorerImageLocation.concat(user.getUserName());
				path=path.concat("/profile/");
				path=path.concat(file.getOriginalFilename());
				System.out.println("Path"+path);
			}
			
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

		
		@Override
		public byte[] downloadProfileImage(String path) throws IOException {
			if(path!=null) {
				System.out.println(path);
				return FileUtils.readFileToByteArray(new File(path));
			}
			throw new ResourceNotFoundException("Image not yet assigned");
		}
//-------------------------------------------------------------------------	


//edit user 
//------------------------------------------------
	@Override
	public UserSignupResponseDto editUser (UserDto request)
	{
		Users user=mapper.map(request, Users.class);
		System.out.println(user);
		Users persistentUser=userDao.save(user);
		return myMapper(persistentUser);
	}
	
	
	@Override
	public PasswordResponseDTO forgetPassword(PasswordRequestDTO dto) {
		Users user = userDao.findByEmail(dto.getEmail()).orElseThrow(()->new ResourceNotFoundException("Email is invalid"));
		user.setPassword(encoder.encode(dto.getPassword()));
		Users persistantUser = userDao.save(user);
		PasswordResponseDTO passwordResponseDTO = new PasswordResponseDTO(persistantUser.getEmail(),persistantUser.getPassword());
		return passwordResponseDTO;
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
	
	public ServiceTransactionResponseDTO myMapper(ServiceTransaction transaction) {
		ServiceTransactionResponseDTO serviceTransactionResponse = new ServiceTransactionResponseDTO();
		serviceTransactionResponse.setId(transaction.getId());
		serviceTransactionResponse.setDateGenTime(transaction.getDateGenTime());
		serviceTransactionResponse.setPaymentMethod(transaction.getPaymentMethod());
		serviceTransactionResponse.setReciverUserId(transaction.getReciverUserId().getId());
		serviceTransactionResponse.setSenderUserId(transaction.getSenderUserId().getId());
		serviceTransactionResponse.setServiceAmount(transaction.getAmount());
		serviceTransactionResponse.setServiceId(transaction.getServiceId().getId());
		serviceTransactionResponse.setSuccess(true);
		return serviceTransactionResponse;
		
	}
	
	//get trasaction list of maestro
	@Override
	public List<ServiceTransactionResponseDTO> getTransactionListOfMaestro(Long userId) {
		Users user = userDao.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User is not valid"));
		List<ServiceTransactionResponseDTO> transactionList = new ArrayList<>();
		for(ServiceTransaction transaction : user.getMaestroTransactionList()) {
			transactionList.add(myMapper(transaction));
		}
		return transactionList;
	}
	
	@Override
	public List<ServiceTransactionResponseDTO> getTransactionListOfExplorer(Long userId) {
		Users user = userDao.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User is not valid"));
		List<ServiceTransactionResponseDTO> transactionList = new ArrayList<>();
		for(ServiceTransaction transaction : user.getExplorerTransactionList()) {
			transactionList.add(myMapper(transaction));
		}
		return transactionList;
		
		
	}

	@Override
	public UserSignupResponseDto getUserDetailsfromUserId(Long userId) {

		Users user=userDao.findById(userId).orElseThrow(()->new ResourceNotFoundException("invalid userId"));
		UserSignupResponseDto userSignupResponseDto=mapper.map(user, UserSignupResponseDto.class);		
		return userSignupResponseDto;
	}

	@Override
	public List<UserSignupResponseDto> getAllMeastroUser() {
	List<UserSignupResponseDto> list = new ArrayList<>();
	List<Object []> obj	=userDao.getAllMaestroUser();
	for(Object o[] : obj ) {
	for(int i = 0 ;i<o.length;i++) {
		Users user = (Users) o[i];
		list.add(myMapper(user));
		}
	}
		return list;
	}
	
	 
	public UserSignupResponseDto myMapper(Users user) {
		UserSignupResponseDto userSignupResponseDto = new UserSignupResponseDto();
		userSignupResponseDto.setFirstName(user.getFirstName());
		userSignupResponseDto.setEmail(user.getEmail());
		userSignupResponseDto.setUserName(user.getUserName());
		userSignupResponseDto.setCreatedAt(user.getCreatedAt());
		userSignupResponseDto.setDob(user.getDob());
		userSignupResponseDto.setDpUrl(user.getDpUrl());
		userSignupResponseDto.setInterest(user.getInterest());
		userSignupResponseDto.setLastName(user.getLastName());
		userSignupResponseDto.setMobileNo(user.getMobileNo());
		userSignupResponseDto.setUserRole(user.getUserRole());
		return userSignupResponseDto;
		
	}
}
