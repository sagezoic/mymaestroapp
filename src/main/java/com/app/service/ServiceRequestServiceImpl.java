package com.app.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.ServceDao;
import com.app.dao.ServiceRequestDao;
import com.app.dao.ServiceTransactionDao;
import com.app.dto.ServiceRequestRequestDTO;
import com.app.dto.ServiceRequestResponseDTO;
import com.app.entities.Servce;
import com.app.entities.ServiceRequest;
import com.app.entities.ServiceTransaction;

import custom_exception.ResourceNotFoundException;


@Service
@Transactional
public class ServiceRequestServiceImpl implements ServiceRequestService {

	@Autowired
	private ServiceRequestDao  serviceRequestDao;
	
	@Autowired
	private ServceDao serviceDao;
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private ServiceTransactionDao serviceTransactionDao;
	
	@Override
	public ServiceRequestResponseDTO addServiceRequest(ServiceRequestRequestDTO request) {
		Servce service = serviceDao.findById(request.getServiceId()).orElseThrow(()->new ResourceNotFoundException("service id is invalid"));
		ServiceTransaction serviceTransaction = serviceTransactionDao.findByServiceId(service);
		ServiceRequest serviceRequest=mapper.map(request,ServiceRequest.class);
		System.out.println(serviceRequest);
		serviceRequest.setServiceId(service);
		serviceRequest.setTransactionId(serviceTransaction);
		serviceRequest.setRequestGenTime(LocalDateTime.now());
		ServiceRequest persistentServiceRequest=serviceRequestDao.save(serviceRequest);
		if(service!=null) {
			service.addServiceRequest(persistentServiceRequest);
		}
		return myMapper(persistentServiceRequest);
	}
	
	@Override
	public List<ServiceRequestResponseDTO> getServiceRequestList(Long serviceId) {
		
		Servce service=serviceDao.findById(serviceId).orElseThrow(()->new ResourceNotFoundException("invalid serviceId"));
		List<ServiceRequestResponseDTO> serviceRequestList=new ArrayList<>();
		for(ServiceRequest ser : service.getServiceList())
		{
			serviceRequestList.add(myMapper(ser));
		}
		return serviceRequestList;
	}
	
	@Override
	public ServiceRequestResponseDTO editServiceRequest(ServiceRequestRequestDTO request) {
		
		ServiceRequest serviceRequest=mapper.map(request, ServiceRequest.class);
		System.out.println(serviceRequest);
		serviceRequest.setServiceId(serviceDao.findById(request.getServiceId()).orElse(null));
		serviceRequest.setRequestGenTime(LocalDateTime.now());
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
		serviceRequestResponseDTO.setTransactionId(serviceRequest.getTransactionId().getId());
		
		return serviceRequestResponseDTO;
		
	}

	

	
	
	

}
