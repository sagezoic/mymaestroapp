package com.app.service;

import java.util.List;

import com.app.dto.ServceRequestDTO;
import com.app.dto.ServceResponseDTO;
import com.app.dto.ServiceTransactionResponseDTO;


public interface ServceService {

	ServceResponseDTO addNewService(ServceRequestDTO request);
	void deleteServce(Long userId , Long serviceId);
	ServceResponseDTO editService(ServceRequestDTO request);
	List<ServceResponseDTO> getUserService(Long userId);
	ServceResponseDTO getUserServiceUsingServiceId(Long serviceId);
	List<ServceResponseDTO> getAllServices();
	List<ServiceTransactionResponseDTO> getServiceTransactionUsingServiceId(Long serviceId);
	
	
	
	
}
