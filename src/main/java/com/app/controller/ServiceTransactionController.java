package com.app.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.ServiceTransactionRequestDTO;
import com.app.service.ServiceTransactionService;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestController
@RequestMapping("/transaction")
@CrossOrigin(origins = "*")
public class ServiceTransactionController {

	@Autowired
	private ServiceTransactionService serviceTransactionService;
	
	public ServiceTransactionController() {
		System.out.println("In the ServiceTransactionController contr");
	}
	
	@PostMapping("/add")
	public ResponseEntity<?> addServiceTransaction(@RequestBody @Valid ServiceTransactionRequestDTO dto){
		System.out.println(dto);
		System.out.println("In the addServiceTransaction");
		serviceTransactionService.addTransaction(dto);
		return ResponseEntity.ok("Success");
	}
	
	
}
