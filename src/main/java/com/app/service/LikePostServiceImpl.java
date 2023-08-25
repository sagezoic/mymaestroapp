package com.app.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.LikePostDao;
import com.app.dao.PostDao;
import com.app.dao.UserDao;
import com.app.dto.LikePostRequestDTO;
import com.app.dto.LikePostResponseDTO;
import com.app.entities.LikePost;
import com.app.entities.Post;
import com.app.entities.Users;

import custom_exception.ResourceNotFoundException;
@Service
@Transactional
public class LikePostServiceImpl implements LikePostService {

	@Autowired
	private LikePostDao likePostDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private PostDao postDao;
	@Override
	public void addLike(LikePostRequestDTO dto) {
		Users user = userDao.findById(dto.getUserId()).orElseThrow(()->new ResourceNotFoundException("User id is invalid"));
		Post post  =  postDao.findById(dto.getPostId()).orElseThrow(()->new ResourceNotFoundException("Post id is invalid"));
		LikePost likePost = new LikePost();
		likePost.setUserId(user);
		likePost.setPostId(post);
		LikePost persistanceLikePost = likePostDao.save(likePost);
		post.setLikePost(persistanceLikePost);
		user.addLikePost(persistanceLikePost);
	}
	
	@Override
	public void removeLike(Long likeId) {
		LikePost like = likePostDao.findById(likeId).orElseThrow(()->new ResourceNotFoundException("likepost id is invalid"));
		Long userId = like.getUserId().getId();
		Users user = userDao.findById(userId).orElseThrow(()->new ResourceNotFoundException("user id is invalid"));
		likePostDao.deleteById(likeId);
		user.removeLikePost(like);
		
	}
	
	@Override
	public List<LikePostResponseDTO> getCountLikesPerPostForUser(Long userId) {
		
		//System.out.println(likePostDao.countLikesPerPostForUser(userId));
		Users user = userDao.findById(userId).orElseThrow(()->new ResourceNotFoundException("user id is invalid"));
		List<LikePostResponseDTO> likePostList = new ArrayList<>();
		for (Object [] arr : likePostDao.countLikesPerPostForUser(user)) {
			System.out.println(((Post)arr[0]).toString());
			System.out.println(arr[1]);
			likePostList.add(myMapper(arr));
		}
		return likePostList;
	}
	
	public LikePostResponseDTO myMapper(Object [] arr) {
		LikePostResponseDTO responseDTO = new LikePostResponseDTO();
		responseDTO.setPostId(((Post)arr[0]).getId());	 
		responseDTO.setLikes((Long)arr[1]);
		return responseDTO;
			
	}
	
	@Override
	public List<LikePostResponseDTO> getCountAllLikesPost() {
		
		List<LikePostResponseDTO> likePostList = new ArrayList<>();
		for (Object [] arr : likePostDao.countAllLikesPost()) {
			System.out.println(((Post)arr[0]).toString());
			System.out.println(arr[1]);
			likePostList.add(myMapper(arr));
		}
		return likePostList;
	}
}
