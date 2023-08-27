package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.CommonResponse;
import com.app.service.FollowersService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/followerfollowing")
@CrossOrigin(origins="*")
public class FollowerFollowingController {

	@Autowired
	private FollowersService followerService;
	
	
	@PostMapping("/addfandf")
	public ResponseEntity<?> addFollowerandFollowing(@RequestParam Long followerId,@RequestParam Long userId)
	{
		System.out.println("in followerfollowing controller "
				+ "with request parameters "+followerId+" and "+ userId);
		String str1=followerService.addFollowers(followerId, userId);
		String str2=followerService.addFollowing(userId, followerId);
		return ResponseEntity.ok(new CommonResponse("", str1+"  "+str2));
	}
	
	@DeleteMapping("/deletefandf")
	public ResponseEntity<?> deleteFollowerandFollowing(@RequestParam Long followerId,@RequestParam Long userId)
	{
		System.out.println("in followerfollowing controller with request parameters " + followerId+ " and "+userId);
		String str1=followerService.deleteFollowers(followerId, userId);
		String str2=followerService.deleteFollowing(followerId, userId);
		return ResponseEntity.ok(new CommonResponse("",str1 +" " + str2 ));
	}
	
}
