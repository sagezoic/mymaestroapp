package com.app.service;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
import com.app.entities.PostType;
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
	public PostResponseDTO addPost(PostRequestDTO dto) throws IOException {
		Users user = userDao.findById(dto.getUserId()).orElseThrow(()->new ResourceNotFoundException("User id invaild"));  
		Post post=mapper.map(dto, Post.class);
		String path=null;
		if(user.getUserRole().toString()=="ROLE_MAESTRO")
		{
			path =maestroImageLocation.concat(user.getUserName());
			path=path.concat("/Post/");
			path=path.concat(dto.getImageFile().getOriginalFilename());
			System.out.println("Path"+path);
				
		}
		else if(user.getUserRole().toString()=="ROLE_EXPLORER")
		{
			path =explorerImageLocation.concat(user.getUserName());
			path=path.concat("/Post/");
			path=path.concat(dto.getImageFile().getOriginalFilename());
			System.out.println("Path "+path);
		}
		
		FileUtils.writeByteArrayToFile(new File(path), dto.getImageFile().getBytes());
		//Post post = postDao.findById(dto.getId()).orElseThrow(()->new ResourceNotFoundException("invalid id"));
		post.setUrlText(path);
		user.addpost(post);
		Post postPersistance = postDao.save(post);
		return myMapper(postPersistance);
				
	}
	
	@Override
	public List<PostResponseDTO> getAllPostForUser(Long userId) {
		Users user = userDao.findById(userId).orElseThrow(()->new ResourceNotFoundException("user is invalid"));
		List<PostResponseDTO> postList = new ArrayList<>();
		
		for(Post post: user.getPost()){
			postList.add(myMapper(post));
		}
		return postList;
	}
	
	PostResponseDTO myMapper(Post postPersistance ) {
		PostResponseDTO postDTO = new PostResponseDTO();
		postDTO.setId(postPersistance.getId());
		postDTO.setCaptionText(postPersistance.getCaptionText());
		postDTO.setPostType(postPersistance.getPostType());
		postDTO.setTimeStamp(postPersistance.getTimeStamp());
		postDTO.setUserId(postPersistance.getUserId().getId());
		postDTO.setUrlText(postPersistance.getUrlText());
		if(postPersistance.getLikePost()!=null)
		postDTO.setPostLikeId(postPersistance.getLikePost().getId());
		return postDTO;
	}
	
	
	@Override
	public List<PostResponseDTO> getAllPost() {
		List<PostResponseDTO> postList = new ArrayList<>();
		List<Object[]> arr = postDao.fetchAlllPost();
		for(Object [] a : arr) {
			postList.add(myMapper(a));
		}
	            
		return postList;
}
	

	PostResponseDTO myMapper(Object [] postObject ) {
		Post post = null;
		post= postDao.findById((Long)postObject[0]).orElseThrow(()->new ResourceNotFoundException("post id invalid"));
		PostResponseDTO postDTO = new PostResponseDTO(); 
		postDTO.setId((Long)postObject[0]);
		postDTO.setCaptionText((String)postObject[1]);
		postDTO.setPostType((PostType)postObject[2]);
		postDTO.setTimeStamp((LocalDateTime)postObject[3]);
		postDTO.setUrlText((String)postObject[4]);
		postDTO.setUserId(((Users)postObject[5]).getId());
		if(post.getLikePost()!=null)
		postDTO.setPostLikeId(post.getLikePost().getId());
		return postDTO;
	}
	
	
	@Override
	public void deletePost(Long userId, Long postId) {
		Users user = userDao.findById(userId).orElseThrow(()->new ResourceNotFoundException("user is invalid"));
		Post post =  postDao.findById(postId).orElseThrow(()->new ResourceNotFoundException("post id is invalid"));
		postDao.deleteById(postId);
		user.removePost(post);
		
	}
	
	@Override
	public PostResponseDTO editPost(Long postId,String caption) throws IOException {
		Post post = postDao.findById(postId).orElseThrow(()->new ResourceNotFoundException("post id is invalid"));
		post.setCaptionText(caption);
		Post persistance = postDao.save(post);
		//return mapper.map(persistance,PostResponseDTO.class);	
		return myMapper(persistance);
	}
}
