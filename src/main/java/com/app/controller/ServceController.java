package com.app.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.CommonResponse;
import com.app.dto.ServceRequestDTO;
import com.app.dto.UserDto;
import com.app.entities.Servce;
import com.app.entities.Users;
//import com.app.dto.servceDTO;
import com.app.service.ServceService;

@RestController
@CrossOrigin(origins="*")
public class ServceController {

	@Autowired
	private ServceService servceService;
	
	public ServceController() {
		System.out.println("in service controller ");
	}
	
	@PostMapping("/service")
	public ResponseEntity<?> addservce(@RequestBody @Valid ServceRequestDTO request)
	{
		System.out.println("in addservice "+ request);
		servceService.addNewService(request);
		return ResponseEntity.ok("success");
	}
	@DeleteMapping("/service/delete/{serviceId}")
	public ResponseEntity<?> deleteServce(@PathVariable Long serviceId)
	{
		System.out.println("in delete serviceId "+ serviceId);
		//servceService.deleteServce(serviceId);
		return ResponseEntity.ok("success");
	}
	
	@PutMapping("/service/edit")
	public ResponseEntity<?> editServce(@RequestBody @Valid ServceRequestDTO request)
	{
		System.out.println("in edit service"+ request);
		servceService.editService(request);
		return ResponseEntity.ok("success");
	}
	
	@GetMapping("/service/{userId}")
	public ResponseEntity<?> getListOfUserServices(@PathVariable Users userId){
		//List<Servce> list = servceService.getUserService(userId); 
		
		return ResponseEntity.ok(new CommonResponse("Success",servceService.getUserService(userId)));
	}
}
