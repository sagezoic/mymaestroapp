package com.app.service;

import java.util.List;

import com.app.dto.ServiceRequestRequestDTO;
import com.app.dto.ServiceRequestResponseDTO;
import com.app.entities.ServiceRequest;

public interface ServiceRequestService {

	ServiceRequestResponseDTO addServiceRequest(ServiceRequestRequestDTO request);
	ServiceRequestResponseDTO editServiceRequest(ServiceRequestRequestDTO request);
	List<ServiceRequestResponseDTO> getServiceRequestList(Long serviceId);
	
}
