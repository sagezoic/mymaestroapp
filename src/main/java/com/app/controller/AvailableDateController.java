package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dao.AvailableDateDao;
import com.app.dto.AvailableDateRequestDTO;
import com.app.dto.CommonResponse;
import com.app.service.AvailableDateService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/date")
@CrossOrigin(origins = "http://localhost:3000")
public class AvailableDateController {

	@Autowired
	private AvailableDateService availableDateService;
	
	@PostMapping("/add")
	public ResponseEntity<?> addAvailableDate(@RequestBody AvailableDateRequestDTO reqdto)
	{
		System.out.println(reqdto);
		
		return ResponseEntity.ok(new CommonResponse("success", availableDateService.addDateSlot(reqdto)));
	}
	
}
