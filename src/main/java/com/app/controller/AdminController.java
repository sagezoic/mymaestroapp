package com.app.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.AdminVerificationforUserRequestDto;
import com.app.dto.CommonResponse;
import com.app.dto.UserDto;
import com.app.service.AdminService;
import com.app.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/admin")
@CrossOrigin(origins="*")
public class AdminController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private AdminService adminService;
	
	@PostMapping("/verifyuser/{userId}")
	public ResponseEntity<?> verifyUser(@PathVariable Long userId)
	{
		System.out.println("in verifyUser with userId " + userId);
		return ResponseEntity.ok(adminService.addAdminVerification(userId));
	}
	
	@PostMapping("/block/{userId}")
	public ResponseEntity<?> blockUser(@PathVariable Long userId)
	{
		System.out.println("in block user method of admin controller with userId = "+userId);
		return ResponseEntity.ok(adminService.blockUser(userId));
	}
	
	@GetMapping("/getuserlist")
	public ResponseEntity<?> getUserList()
	{
		System.out.println("in getUserList method of admin controller ");
		return ResponseEntity.ok(adminService.getAllUsersList());
	}
	@DeleteMapping("/removereportedpost/{postId}")
	public ResponseEntity<?> removeReportedPost(@PathVariable Long postId)
	{
		System.out.println("inside admin controller in removeReportedPost with postId = "+ postId );
		return ResponseEntity.ok(new CommonResponse("deletetion ", adminService.removeReportedPost(postId)));
	}
	@GetMapping("/getallreportedpost")
	public ResponseEntity<?> getAllReportedPost()
	{
		System.out.println("inside admin controller in getAllReportedPost ");
		
		return ResponseEntity.ok(new CommonResponse("success",adminService.getAllReportedPost()));
	}
}
