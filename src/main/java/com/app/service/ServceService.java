package com.app.service;

import com.app.dto.ServceResponseDTO;
import com.app.dto.ServiceRequestRequest;
import com.app.dto.ServiceRequestResponse;
import com.app.entities.Servce;
import com.app.entities.Users;

import java.util.List;

import com.app.dto.CommonResponse;
import com.app.dto.ServceRequestDTO;

public interface ServceService {

	ServceResponseDTO addNewService(ServceRequestDTO request);
//	void deleteServce(Long servceId);
	//ServceResponseDTO editService(ServceRequestDTO request);
	
	//void delServce(Long userId,Long servceId);
	 //ServceResponseDTO getUserService (Long userId);
	//public String deleteServceDetails(Long UserId, Long serviceId);

	//void deleteServce(Long servceId);
	void deleteServce(Long userId , Long serviceId);
	ServceResponseDTO editService(ServceRequestDTO request);
	//List<Servce> getUserService(Long userId);
	 //ServceResponseDTO getUserService (Long userId);
	List<ServceResponseDTO> getUserService(Long userId);
	
	ServiceRequestResponse addServiceRequest(ServiceRequestRequest dto);
}
