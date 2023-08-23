package com.app.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.ServceDao;
import com.app.dao.ServiceRequestDao;
import com.app.dto.ServiceRequestRequestDTO;
import com.app.dto.ServiceRequestResponseDTO;
import com.app.entities.Servce;
import com.app.entities.ServiceRequest;


@Service
@Transactional
public class ServiceRequestServiceImpl implements ServiceRequestService {

	@Autowired
	private ServiceRequestDao  serviceRequestDao;
	
	@Autowired
	private ServceDao serviceDao;
	
	@Autowired
	private ModelMapper mapper;
	
	@Override
	public ServiceRequestResponseDTO addServiceRequest(ServiceRequestRequestDTO request) {
		// TODO Auto-generated method stub
		
		ServiceRequest serviceRequest=mapper.map(request,ServiceRequest.class);
		System.out.println(serviceRequest);
		serviceRequest.setServiceId(serviceDao.findById(request.getServiceId()).orElse(null));
		ServiceRequest persistentServiceRequest=serviceRequestDao.save(serviceRequest);
		Servce service=serviceDao.findById(request.getServiceId()).orElse(null);
		if(service!=null) {
			service.addServiceRequest(persistentServiceRequest);
		}
		return myMapper(persistentServiceRequest);
	}
	
	ServiceRequestResponseDTO myMapper(ServiceRequest serviceRequest) {
		ServiceRequestResponseDTO serviceRequestResponseDTO = new ServiceRequestResponseDTO();
		serviceRequestResponseDTO.setId(serviceRequest.getId());
		serviceRequestResponseDTO.setSlotDate(serviceRequest.getSlotDate());
		serviceRequestResponseDTO.setDescription(serviceRequest.getDescription());
		serviceRequestResponseDTO.setMaestroUserId(serviceRequest.getMaestroUserId());
		serviceRequestResponseDTO.setExplorerUserId(serviceRequest.getExplorerUserId());
		serviceRequestResponseDTO.setServiceId(serviceRequest.getServiceId().getId());
		serviceRequestResponseDTO.setStatus(serviceRequest.getStatus());
		serviceRequestResponseDTO.setRequestGenTime(serviceRequest.getRequestGenTime());
		serviceRequestResponseDTO.setTransactionId(serviceRequest.getTransactionId());
		
		return serviceRequestResponseDTO;
		
	}

}
