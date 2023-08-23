package com.app.controller;

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
import com.app.dto.LikePostRequestDTO;
import com.app.service.LikeIdService;
import com.app.dto.CommonResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/likepost")
@CrossOrigin(origins = "http://localhost:3000")
public class LikeIdController {
 
	@Autowired
	private LikeIdService likeIdService;
	
	
	
	@PostMapping("/add")
	public ResponseEntity<?> addLike(@RequestBody LikePostRequestDTO dto){
		System.out.println(dto);
		System.out.println("add like controller");
		likeIdService.addLike(dto);
		return ResponseEntity.ok("success");
	}
	
	@DeleteMapping("/delete/{likePostId}")
	public ResponseEntity<?> removePostLike(@PathVariable Long likePostId){
		System.out.println("likepostId "+likePostId);
		System.out.println("remove like controller");
		likeIdService.removeLike(likePostId);
		return ResponseEntity.ok("success");
	}
	
	@GetMapping("/getpostlike/{userId}")
	public ResponseEntity<?> getLikePost(@PathVariable Long userId){
		System.out.println("likepostId "+userId);
		System.out.println("get likepost controller");
		System.out.println(likeIdService.getLikePost(userId));
		return ResponseEntity.ok(new CommonResponse("success",likeIdService.getLikePost(userId)));
	}
	
	
}
