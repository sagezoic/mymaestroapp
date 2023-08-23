package com.app.service;

import com.app.dto.ServiceRequestRequestDTO;
import com.app.dto.ServiceRequestResponseDTO;
import com.app.entities.ServiceRequest;

public interface ServiceRequestService {

	ServiceRequestResponseDTO addServiceRequest(ServiceRequestRequestDTO request);
}
