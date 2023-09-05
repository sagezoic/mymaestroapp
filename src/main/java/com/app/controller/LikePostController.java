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
import com.app.service.LikePostService;
import com.app.dto.CommonResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/likepost")
@CrossOrigin(origins = "*")
public class LikePostController {
 
	@Autowired
	private LikePostService likePostService;
	
	
	
	@PostMapping("/add")
	public ResponseEntity<?> addLike(@RequestBody LikePostRequestDTO dto){
		System.out.println(dto);
		System.out.println("add like controller");
		likePostService.addLike(dto);
		return ResponseEntity.ok("success");
	}
	
	@DeleteMapping("/delete/{likePostId}")
	public ResponseEntity<?> removePostLike(@PathVariable Long likePostId){
		System.out.println("likepostId "+likePostId);
		System.out.println("remove like controller");
		likePostService.removeLike(likePostId);
		return ResponseEntity.ok("success");
	}
	
	@GetMapping("/getpostlike/{userId}")
	public ResponseEntity<?> getLikePost(@PathVariable Long userId){
		System.out.println("likepostId "+userId);
		System.out.println("get likepost controller");
		//System.out.println(likeIdService.getLikePost(userId));
		return ResponseEntity.ok(new CommonResponse("success",likePostService.getCountLikesPerPostForUser(userId)));
	}
	
	@GetMapping("/getallpostlike")
	public ResponseEntity<?> getAllPostLike(){
		System.out.println("get likepost controller");
		
		return ResponseEntity.ok(new CommonResponse("success",likePostService.getCountAllLikesPost()));
	}
}
