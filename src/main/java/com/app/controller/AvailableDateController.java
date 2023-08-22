package com.app.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dao.AvailableDateDao;
import com.app.dto.AvailableDateRequestDTO;
import com.app.dto.CommonResponse;
import com.app.service.AvailableDateService;

import lombok.experimental.Delegate;

@RestController
@RequestMapping("/date")
@CrossOrigin(origins = "http://localhost:3000")
public class AvailableDateController {

	@Autowired
	private AvailableDateService availableDateService;
	
	@PostMapping("/add")
	public ResponseEntity<?> addAvailableDate(@RequestBody AvailableDateRequestDTO dto)
	{
		System.out.println(dto);
		System.out.println("in addAvailable method");
		return ResponseEntity.ok(new CommonResponse("success", availableDateService.addDateSlot(dto)));
	}
	
	@GetMapping("/{serviceId}/getalldate")
	public ResponseEntity<?> getAllAvailableDate(@PathVariable Long serviceId){
		System.out.println("Service Id "+ serviceId );
		System.out.println("in getAllAvailableDate method");
		return ResponseEntity.ok(new CommonResponse("Success",availableDateService.getAllAvailableDate(serviceId)));
	}
	
	@DeleteMapping("/delete")	 
	public ResponseEntity<?> deleteAvailableDate(@RequestBody AvailableDateRequestDTO dto){
		System.out.println("serviceID :" +dto.getServiceId()+" availableDateId"+ dto.getId());
		System.out.println("in deleteAvailableDate method");
		availableDateService.deleteAvailableDate(dto.getServiceId(), dto.getId());
		return ResponseEntity.ok("Successfully deleted AvailableDate");
	}
	
	@PutMapping("/edit")
	public  ResponseEntity<?> editAvailableDate(@RequestBody @Valid AvailableDateRequestDTO dto){
		System.out.println(dto);
		System.out.println("in addAvailable method");
		return ResponseEntity.ok(new CommonResponse("Successfully Updated",availableDateService.editDateSolt(dto)));
		
	}
}
