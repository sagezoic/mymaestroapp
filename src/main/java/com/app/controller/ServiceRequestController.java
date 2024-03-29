package com.app.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.CommonResponse;
import com.app.dto.ServiceRequestRequestDTO;
import com.app.service.ServiceRequestService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/servicerequest")
@CrossOrigin(origins = "*")
public class ServiceRequestController {

	@Autowired
	private ServiceRequestService serviceRequestService;
	
	public ServiceRequestController()
	{
		System.out.println("in serviceRequest controller");
	}
	
	@PostMapping("/add")
	public ResponseEntity<?> addServiceRequest(@RequestBody @Valid ServiceRequestRequestDTO serviceRequestRequest)
	{
		System.out.println("in addServiceRequest "+ serviceRequestRequest);
		return ResponseEntity.ok(new CommonResponse("success",serviceRequestService.addServiceRequest(serviceRequestRequest)));
	}
	
	@PutMapping("/edit")
	public ResponseEntity<?> editServiceRequest(@RequestBody @Valid ServiceRequestRequestDTO serviceRequestRequest)
	{
		System.out.println("in editServiceRequest "+ serviceRequestRequest);
		return ResponseEntity.ok(new CommonResponse("success",serviceRequestService.editServiceRequest(serviceRequestRequest)));
	}
	
	@GetMapping("/get/{serviceId}")
	public ResponseEntity<?> getServiceRequest(@PathVariable Long serviceId)
	{
		System.out.println("in getServiceRequest in List with serviceId = "+ serviceId);
		return ResponseEntity.ok(new CommonResponse("success",serviceRequestService.getServiceRequestList(serviceId)));
	}
}
