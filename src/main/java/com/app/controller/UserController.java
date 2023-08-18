package com.app.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import static org.springframework.http.MediaType.*;

import java.io.IOException;

import com.app.dto.CommonResponse;
import com.app.dto.UserDto;
import com.app.service.UserService;



@RestController
@RequestMapping("/users")
@CrossOrigin(origins="http://localhost:3000")
public class UserController {

	@Autowired
	private UserService userService;
	
	public UserController() {
		System.out.println("in user controller");
	}
	
	@PostMapping("/role")
	public ResponseEntity<?> addRoleDetails(@RequestBody @Valid UserDto user)
	{
		System.out.println("in userAdd Role Details");
		
		return ResponseEntity.ok(new CommonResponse("success", userService.addRoleDetails(user)));
	}
	
	@PostMapping("/details")
	public ResponseEntity<?> addBasicDetails(@RequestBody @Valid UserDto user)
	{
		System.out.println("in userAdd Role Details");
		
		return ResponseEntity.ok(new CommonResponse("success", userService.addBasicDetails(user)));
	}
	
	@PostMapping("/interest")
	public ResponseEntity<?> addInterest(@RequestBody @Valid UserDto user)
	{
		System.out.println("in userAdd Role Details");
		
		return ResponseEntity.ok(new CommonResponse("success", userService.addInterest(user)));
	}
	
	@PostMapping(value="/{userId}/image",consumes = "multipart/form-data")
	public ResponseEntity<?> uploadProfileImage(@PathVariable Long userId,@RequestParam MultipartFile imagefile) throws IOException{
		
		System.out.println("in image upload"+userId);
		return ResponseEntity.ok(new CommonResponse("Success",userService.uploadProfileImage(userId, imagefile)));
	}
	
	@GetMapping(value = "/{userId}/image", produces = { IMAGE_GIF_VALUE, 
			IMAGE_JPEG_VALUE, IMAGE_PNG_VALUE })
	public ResponseEntity<?> downloadEmpImage(@PathVariable Long userId) throws IOException {
		System.out.println("in img download " + userId);
		return ResponseEntity.ok(userService.downloadProfileImage(userId));
	}
	
	@PostMapping(value="/delete/{userId}")
	public ResponseEntity<?> deleteUser(@PathVariable Long userId ){
		
		System.out.println("in user delete controller");
		userService.deleteUser(userId);
		return ResponseEntity.ok("user deleted Successfully");
	}
	
	@PostMapping("/edit")
	public ResponseEntity<?> editUser(@RequestBody UserDto user){
		
		System.out.println("in user edit controller");
		userService.editUser(user);
		return ResponseEntity.ok("user data edited Successfully");
	}
}
