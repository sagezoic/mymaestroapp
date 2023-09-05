package com.app.controller;

import static org.springframework.http.MediaType.IMAGE_GIF_VALUE;
import static org.springframework.http.MediaType.IMAGE_JPEG_VALUE;
import static org.springframework.http.MediaType.IMAGE_PNG_VALUE;

import java.io.IOException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.CommonResponse;
import com.app.dto.PostRequestDTO;
import com.app.service.PostService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/post")
@CrossOrigin(origins = "*")
public class PostController {
	@Autowired
	private PostService postService;

	public PostController() {
		System.out.println("In Post Controller");
	}

	@PostMapping(value = "/image", consumes = "multipart/form-data")
	public ResponseEntity<?> addPost(@ModelAttribute @Valid PostRequestDTO dto) throws IOException {
		System.out.println(dto);
		System.out.println("in addPost mathod");
		return ResponseEntity.ok(new CommonResponse("Success", postService.addPost(dto)));
	}

	@GetMapping("/{userId}/allpost")
	public ResponseEntity<?> getAllPost(@PathVariable Long userId) {
		System.out.println("in get all post of user method");
		return ResponseEntity.ok(new CommonResponse("Success", postService.getAllPostForUser(userId)));
	}
	
	@GetMapping(value="/findimage", produces = { IMAGE_GIF_VALUE, 
			IMAGE_JPEG_VALUE, IMAGE_PNG_VALUE })
	public ResponseEntity<?> getpost(@RequestParam String path) throws IOException
	{
		System.out.println("in post controller getpost method "+ path);
		return ResponseEntity.ok(postService.downloadPostImage(path));
	}
	
	@GetMapping("/getallpost")
	public ResponseEntity<?> getAllPost() {
		System.out.println("in get all post method");
		return ResponseEntity.ok(new CommonResponse("Success", postService.getAllPost()));
	}

//	@DeleteMapping("/deletepost")
//	public ResponseEntity<?> deletePost(@RequestBody PostRequestDTO dto ){
//		System.out.println(dto);
//			postService.deletePost(dto.getUserId(), dto.getId());
//		return ResponseEntity.ok("Success");
//	}

	@DeleteMapping("/deletepost")
	public ResponseEntity<?> deletePost(@RequestParam Long userId, @RequestParam Long postId) {
		// System.out.println(dto);
		postService.deletePost(userId, postId);
		return ResponseEntity.ok("Success");
	}

	@PutMapping("/edit")
	public ResponseEntity<?> editPost(@RequestParam Long postId, @RequestParam String caption) throws IOException {

		return ResponseEntity.ok(new CommonResponse("Success", postService.editPost(postId, caption)));
	}
}
