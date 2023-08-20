package com.app.controller;

import java.io.IOException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
	
}
