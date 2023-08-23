package com.app.service;

import com.app.dto.ServceResponseDTO;
import com.app.entities.Servce;
import com.app.entities.Users;
import java.util.List;
import com.app.dto.ServceRequestDTO;


public interface ServceService {

	ServceResponseDTO addNewService(ServceRequestDTO request);
	void deleteServce(Long userId , Long serviceId);
	ServceResponseDTO editService(ServceRequestDTO request);
	List<ServceResponseDTO> getUserService(Long userId);
	ServceResponseDTO getUserServiceUsingServiceId(Long serviceId);
	
}
