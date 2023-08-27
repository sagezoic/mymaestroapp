package com.app.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.FollowersDao;
import com.app.dao.FollowingDao;
import com.app.dao.UserDao;
import com.app.dto.FollowersResponseDTO;
import com.app.entities.Followers;
import com.app.entities.Following;
import com.app.entities.Users;

import custom_exception.ResourceNotFoundException;
@Service
@Transactional
public class FollowersServiceImpl implements FollowersService {
	@Autowired
	private FollowersDao followersDao;
	
	@Autowired
	private FollowingDao followingDao;
	
	@Autowired
	private UserDao userDao;
	
	
	@Override
	public String addFollowers(Long followersId , Long userId) 
	{

		//Users user=userDao.findById(userId).orElseThrow(()->new ResourceNotFoundException("invalid userId"));
		Followers follower=new Followers();
		follower.setUserId(userId);
		follower.setFollowersId(userDao.findById(followersId).orElseThrow(()-> new ResourceNotFoundException("invalid userId")));
		followersDao.save(follower);
		return "successfully added follower";
	}
	
	@Override
	public String addFollowing(Long followingId, Long userId) {
	
		Following following=new Following();
		following.setUserId(userId);
		following.setFollowingId(userDao.findById(followingId).orElseThrow(()-> new ResourceNotFoundException("invalid userId")));
		followingDao.save(following);
		return "successfully added following";
	}
	
	
	@Override
	public String deleteFollowers(Long followersId, Long userId) {
		System.out.println("inside Followerservice deletefollowers method");
		List<Followers> followersList=followersDao.findByUserId(userId);
		System.out.println("at point 1");
		Users user=userDao.findById(userId).orElseThrow(()->new ResourceNotFoundException("invalid userId"));
		System.out.println("at point 2");

		// Followers followertodelete=new Followers();
		Long idToFindFollower=null;
		System.out.println("at point 3");

		for(Followers f: followersList)
		{
			System.out.println("at point 4");

			System.out.println(f.getFollowersId().getId());
			System.out.println("at point 5");
			if(f.getFollowersId().getId()==followersId)
			{
				System.out.println("at point 6");
//				followertodelete.setId(f.getId());
//				followertodelete.setUserId(userId);
//				followertodelete.setFollowersId(f.getFollowersId());
				idToFindFollower=f.getId();
			}
		}
		System.out.println("at point 7");
		Followers persistentfollower=followersDao.findById(idToFindFollower).orElseThrow(()->new ResourceNotFoundException("invalid follower id"));
		System.out.println("at point 8");
		followersDao.delete(persistentfollower);
		System.out.println("at point 9");
		user.removeFollowers(persistentfollower);
		System.out.println("at point 10");
		return "successfully deleted followers";
	}

	@Override
	public String deleteFollowing(Long followingId, Long userId) {
		System.out.println("inside followersService deleteFollowing method");
		List<Following> followingList=followingDao.findByUserId(followingId);
		//List<Following> followingList=followingDao.findByFollowingId(followingId);
		System.out.println("at point 10");
		for(Following f:followingList)
		{
			System.out.println("at point 10.1");
			System.out.println(f);
			
		}
		Users user=userDao.findById(userId).orElseThrow(()->new ResourceNotFoundException("invalid userId"));
		System.out.println("at point 11");
		Long idToFindFollowing=null;
		System.out.println("at point 12");
		for(Following f: followingList)
		{
			System.out.println("at point 13");
			if(f.getFollowingId().getId()==userId)
			{
				System.out.println("at point 14");
				idToFindFollowing=f.getId();
				System.out.println("at point 15");
			}
		}
		System.out.println("at point 16");
		Following persistentfollowing=followingDao.findById(idToFindFollowing).orElseThrow(()-> new ResourceNotFoundException("invalid following id"));
		System.out.println("at point 17");
		followingDao.delete(persistentfollowing);
		System.out.println("at point 18");
		user.removeFollowing(persistentfollowing);
		System.out.println("at point 19");
		return "successfully deleted following";
	}
	
	
	@Override
	public List<FollowersResponseDTO> getListOfFollwersForSUser(Long userId) {
		Users user = userDao.findById(userId).orElseThrow(()->new ResourceNotFoundException("User Id is Invalid"));
		List<FollowersResponseDTO> list = new ArrayList<>();
		for(Followers follow : user.getFollowersList()) {
			list.add(myMapper(follow));
		}
		
		return list;
	}
	
	
	public FollowersResponseDTO myMapper(Followers follow) {
		FollowersResponseDTO followersResponseDTO = new FollowersResponseDTO();
		followersResponseDTO.setUserId(follow.getUserId());
		if(follow.getFollowersId()!=null)
		followersResponseDTO.setFollowersId(follow.getFollowersId().getId());
		return followersResponseDTO; 
		
	}


	
	
}
