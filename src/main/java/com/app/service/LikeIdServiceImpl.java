package com.app.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.LikePostDao;
import com.app.dao.PostDao;
import com.app.dao.UserDao;
import com.app.dto.LikePostRequestDTO;
import com.app.entities.LikePost;
import com.app.entities.Post;
import com.app.entities.Users;

import custom_exception.ResourceNotFoundException;
@Service
@Transactional
public class LikeIdServiceImpl implements LikeIdService {

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
	public List<Object[]> getLikePost(Long userId) {
		
		return likePostDao.countLikesPerPostForUser(userId);
	}
}
