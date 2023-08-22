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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.CommonResponse;
import com.app.dto.ServceRequestDTO;
import com.app.dto.ServiceRequestRequest;
import com.app.dto.UserDto;
import com.app.entities.Servce;
import com.app.entities.Users;
//import com.app.dto.servceDTO;
import com.app.service.ServceService;

@RestController
@RequestMapping("/service")
@CrossOrigin(origins = "*")
public class ServceController {

	@Autowired
	private ServceService servceService;

	public ServceController() {
		System.out.println("in service controller ");
	}

	@PostMapping("/add")
	public ResponseEntity<?> addservce(@RequestBody @Valid ServceRequestDTO request) {
		System.out.println("in addservice " + request);
		servceService.addNewService(request);
		return ResponseEntity.ok("success");
	}
//	@DeleteMapping("/service/delete/{serviceId}")
//	public ResponseEntity<?> deleteServce(@PathVariable Long serviceId)
//	{
//		System.out.println("in delete serviceId "+ serviceId);
//		servceService.deleteServce(serviceId);
//		return ResponseEntity.ok("success");
//	}

	@DeleteMapping("/delete")
	public ResponseEntity<?> deleteServce(@RequestBody ServceRequestDTO dto) {
		// System.out.println("in delete serviceId "+ serviceId);
		// servceService.deleteServce(serviceId);

		System.out.println("in delete serviceId " + dto.getId());
		servceService.deleteServce(dto.getUserId(), dto.getId());
		return ResponseEntity.ok("success");
	}

	@PutMapping("/edit")
	public ResponseEntity<?> editServce(@RequestBody @Valid ServceRequestDTO request) {
		System.out.println("in edit service" + request);
		servceService.editService(request);
		return ResponseEntity.ok("success");
	}

	@GetMapping("/{userId}")
	public ResponseEntity<?> getListOfUserServices(@PathVariable Long userId) {

		System.out.println("inside controller of getLisOfUserServices");

		return ResponseEntity.ok(new CommonResponse("Success", servceService.getUserService(userId)));

	}
	
	@PostMapping("/addrequest")
	public ResponseEntity<?> addServiceRequest(@RequestBody @Valid ServiceRequestRequest dto){
		
		return ResponseEntity.ok(new CommonResponse("Success",servceService.addServiceRequest(dto)));
	}
}
