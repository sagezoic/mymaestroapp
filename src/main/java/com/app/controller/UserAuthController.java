package com.app.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.AuthRequest;
import com.app.dto.AuthResponse;
import com.app.dto.CommonResponse;
import com.app.dto.UserDto;
import com.app.jwt_utils.JwtUtils;
import com.app.service.ServceService;
import com.app.service.UserService;

@RestController
//@RequestMapping("/users")
@CrossOrigin(origins="*")
public class UserAuthController {
	
	@Autowired
	private UserService userService; 
	
	@Autowired
	private ServceService servceService;
	
	// dep : auth mgr
	@Autowired
	private AuthenticationManager manager;
	
	
	//dep : jwt utils 
	@Autowired
	private JwtUtils jwtUtils;

	public UserAuthController() {
		System.out.println("in user auth controller");
	}

	// http://host:port/users/signin , method=POST ,
	// req : AuthRequest : DTO
	// resp : AuthResp : message , jwt
	@PostMapping("/login")
	public ResponseEntity<?> userSignIn(@RequestBody @Valid AuthRequest request) {
		System.out.println("in user signin " + request);
		// wrap use's un verified credentials in the auth object
		UsernamePasswordAuthenticationToken authToken = 
				new UsernamePasswordAuthenticationToken(request.getEmail(),
				request.getPassword());
		// invoke Auth mgr's authenticate method
		Authentication verifiedCredentials = manager.authenticate(authToken);
		//=>no auth exc => auth success , generate auth resp containing genearated JWT
		return ResponseEntity.ok(new AuthResponse("Successs",
				jwtUtils.generateJwtToken(verifiedCredentials)));
	}
	
	@PostMapping("/signup")
	public ResponseEntity<?> userSignup(@RequestBody @Valid UserDto request)
	{
		System.out.println("in usersignup "+ request);
		//userService.registerUser(request);
		
		return ResponseEntity.ok(new CommonResponse("Success", userService.registerUser(request)));
	}
	
}
