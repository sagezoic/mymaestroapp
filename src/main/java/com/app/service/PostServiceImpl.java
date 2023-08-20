package com.app.service;

import java.io.File;
import java.io.IOException;

import javax.annotation.PostConstruct;

import org.modelmapper.ModelMapper;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.app.dao.PostDao;
import com.app.dao.UserDao;
import com.app.dto.PostRequestDTO;
import com.app.dto.PostResponseDTO;
import com.app.dto.UserSignupResponseDto;
import com.app.entities.Post;
import com.app.entities.Users;

import custom_exception.ResourceNotFoundException;
@Service
@Transactional
public class PostServiceImpl implements PostService {
	@Autowired
	private PostDao postDao;
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private UserDao userDao;
	@Value("${upload.locationmaestro}")
	private String maestroImageLocation;
	
	@Value("${upload.locationexplorer}")
	private String explorerImageLocation;
	
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
	public PostResponseDTO addPost(Long userId, MultipartFile file,PostRequestDTO dto) throws IOException {
		Users user = userDao.findById(userId).orElseThrow(()->new ResourceNotFoundException("User id invaild"));  
		
		String path=null;
		if(user.getUserRole().toString()=="ROLE_MAESTRO")
		{
			path =maestroImageLocation.concat(user.getUserName());
			path=path.concat("/Post/");
			path=path.concat(file.getOriginalFilename());
			System.out.println("Path"+path);
				
		}
		else if(user.getUserRole().toString()=="ROLE_EXPLORER")
		{
			path =explorerImageLocation.concat(user.getUserName());
			path=path.concat("/Post/");
			path=path.concat(file.getOriginalFilename());
			System.out.println("Path"+path);
		}
		
		FileUtils.writeByteArrayToFile(new File(path), file.getBytes());
		Post post = postDao.findById(dto.getId()).orElseThrow(()->new ResourceNotFoundException("invalid id"));
		post.setUrlText(path);
		user.addpost(post);
		Post persistance = postDao.save(post);
		return mapper.map(persistance,PostResponseDTO.class);	
				
	}
}
