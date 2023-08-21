package com.app.controller;

import java.io.IOException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.app.dto.CommonResponse;
import com.app.dto.PostRequestDTO;
import com.app.service.PostService;

@RestController
@RequestMapping("/post")
@CrossOrigin(origins="http://localhost:3000")
public class PostController {
	@Autowired
	private PostService postService;
	
	public PostController() {
		System.out.println("In Post Controller");
	}
	

	@PostMapping(value="/image",consumes = "multipart/form-data")
	public ResponseEntity<?> addPost(@ModelAttribute @Valid PostRequestDTO dto) throws IOException{
		System.out.println(dto);
		System.out.println("in addPost mathod");
		return ResponseEntity.ok(new CommonResponse("Success", postService.addPost(dto)));
	}
	
	@GetMapping("/{userId}/allpost")
	public ResponseEntity<?> getAllPost(@PathVariable Long userId){
		System.out.println("in get all post method");
		return ResponseEntity.ok(new CommonResponse("Success",postService.getAllPost(userId)));
	}
	
//	@DeleteMapping("/deletepost")
//	public ResponseEntity<?> deletePost(@RequestBody PostRequestDTO dto ){
//		System.out.println(dto);
//			postService.deletePost(dto.getUserId(), dto.getId());
//		return ResponseEntity.ok("Success");
//	}
	
	@DeleteMapping("/deletepost")
	public ResponseEntity<?> deletePost(@RequestParam Long userId , @RequestParam Long postId){
		//System.out.println(dto);
			postService.deletePost(userId, postId);
		return ResponseEntity.ok("Success");
	}
	
	@PutMapping("/edit")
	public ResponseEntity<?> editPost(@RequestParam Long postId,@RequestParam String caption ) throws IOException{
		
		return ResponseEntity.ok(new CommonResponse("Success",postService.editPost(postId, caption)));
	}
}
