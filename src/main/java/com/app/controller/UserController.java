package com.app.controller;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.AuthRequest;
import com.app.dto.AuthResp;
import com.app.dto.SignupRequestDTO;
import com.app.entities.Users;
import com.app.service.UserService;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins="http://localhost:3000")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping
	public List<Users> listAllUsrs()
	{
		System.out.println("in list users");
		return userService.getAllUsers();
	}
	
	@PostMapping
	public ResponseEntity<?> addNewUser(@RequestBody @Valid SignupRequestDTO signupRequestDto)
	{
		System.out.println("in add new user"+ signupRequestDto);
		return ResponseEntity.
				status(HttpStatus.CREATED).
				body(userService.addUser(signupRequestDto));
	}
	
	@PostMapping("/signin")
	public ResponseEntity<?> signInUser(@RequestBody @Valid AuthRequest request)
	{
		System.out.println("in singin user request : "+request);
		AuthResp resp=userService.signInUser(request);
		return ResponseEntity.ok(resp);	}
}
