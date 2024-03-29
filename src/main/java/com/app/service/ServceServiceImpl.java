package com.app.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.ServceDao;
import com.app.dao.ServiceRequestDao;
import com.app.dao.UserDao;
import com.app.dto.AllServceResponseDTO;
import com.app.dto.ServceRequestDTO;
import com.app.dto.ServceResponseDTO;
import com.app.dto.ServiceTransactionResponseDTO;
import com.app.entities.Servce;
import com.app.entities.ServiceTransaction;
import com.app.entities.Users;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import custom_exception.ResourceNotFoundException;


@Service
@Transactional
public class ServceServiceImpl implements ServceService{

	private static final Logger infoLogger = LoggerFactory.getLogger("infor logger");
	private static final Logger debugLogger = LoggerFactory.getLogger("debug logger");
	private static final Logger errorLogger = LoggerFactory.getLogger("error logger");
	
	@Autowired
	private ServceDao servceDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private ServiceRequestDao serviceRequestDao;
	@Override
	public ServceResponseDTO addNewService(ServceRequestDTO request) {
	    
		debugLogger.debug("Entering addNewService method");
		Servce ser=mapper.map(request,Servce.class);
	    debugLogger.debug("Mapped request to Service entity: {}", ser);
		System.out.println(ser);
		ser.setUserId(userDao.findById(request.getUserId()).orElse(null));
		ser.setServiceCreatedAt(LocalDateTime.now());
		Servce persistentser=servceDao.save(ser);
		Users user=userDao.findById(request.getUserId()).orElse(null);
	    debugLogger.debug("Retrieved user with ID {}: {}", request.getUserId(), user);
		if(user!=null) {
			user.addServces(persistentser);
	        debugLogger.debug("Saved Service entity: {}", persistentser);

		}
		
	    debugLogger.debug("Exiting addNewService method");
	    errorLogger.error("hello error");
	    infoLogger.info("hello info");
		return myMapper(persistentser);
	}

	@Override
	public ServceResponseDTO editService(ServceRequestDTO request) {
		
		Servce ser=mapper.map(request, Servce.class);
		System.out.println(ser);
		ser.setUserId((userDao.findById(request.getUserId()).orElse(null)));
		Servce persistentser=servceDao.save(ser);
		Users user=userDao.findById(request.getUserId()).orElse(null);
		if(user!=null) {
			user.addServces(persistentser);
		}
		return myMapper(persistentser);
	}

	


	@Override
	public void deleteServce(Long userId, Long serviceId) {
		Users user = userDao.findById(userId).orElseThrow(()->new ResourceNotFoundException("Invalid user"));
		Servce service =servceDao.findById(serviceId).orElseThrow(()->new ResourceNotFoundException("Invalid service id"));
		servceDao.deleteById(serviceId);
		user.removeServce(service);
		
	}
	@Override
	public List<ServceResponseDTO> getUserService(Long userId) {
		Users user = userDao.findById(userId).orElseThrow(()->new ResourceNotFoundException("user invalid"));
		List<ServceResponseDTO> serviceList = new ArrayList<>();
		for(Servce service : user.getServces()){
			serviceList.add(myMapper(service));
		}
		return serviceList;
	
	}
	
	@Override
	public List<AllServceResponseDTO> getAllServices() {
		List<AllServceResponseDTO> responseDTOList = new ArrayList<>();
		List<Servce> alllist=servceDao.findAll();
		for(Servce service : alllist) {
			Users maestro = userDao.findById(service.getUserId().getId()).orElseThrow(()-> new ResourceNotFoundException("Invalid user id"));
			responseDTOList.add(myMapper(service,maestro));
		}
		return responseDTOList;
	}
	
	AllServceResponseDTO myMapper(Servce service,Users maestro) {
		AllServceResponseDTO serviceDTO = new AllServceResponseDTO();
		serviceDTO.setId(service.getId());
		serviceDTO.setServiceTitle(service.getServiceTitle());
		serviceDTO.setPriceToken(service.getPriceToken());
		serviceDTO.setServicetype(service.getServicetype());
		serviceDTO.setUserId(service.getUserId().getId());
		serviceDTO.setTimePeriod(service.getTimePeriod());
		serviceDTO.setDescription(service.getDescription());
		serviceDTO.setServiceCategory(service.getServiceCategory());
		serviceDTO.setServiceCreatedAt(service.getServiceCreatedAt());
		serviceDTO.setFirstName(maestro.getFirstName());
		serviceDTO.setLastName(maestro.getLastName());
		serviceDTO.setUserName(maestro.getUserName());
		serviceDTO.setDpUrl(maestro.getDpUrl());
		return serviceDTO;
		
	}
	
	
	
	@Override
	public ServceResponseDTO getUserServiceUsingServiceId(Long serviceId) {
		Servce service=servceDao.findById(serviceId).orElseThrow(()->new ResourceNotFoundException("invalid serviceid"));
		return myMapper(service);
	}
	
	
	ServceResponseDTO myMapper(Servce service) {
		ServceResponseDTO serviceDTO = new ServceResponseDTO();
		serviceDTO.setId(service.getId());
		serviceDTO.setServiceTitle(service.getServiceTitle());
		serviceDTO.setPriceToken(service.getPriceToken());
		serviceDTO.setServicetype(service.getServicetype());
		serviceDTO.setUserId(service.getUserId().getId());
		serviceDTO.setTimePeriod(service.getTimePeriod());
		serviceDTO.setDescription(service.getDescription());
		serviceDTO.setServiceCategory(service.getServiceCategory());
		serviceDTO.setServiceCreatedAt(service.getServiceCreatedAt());
		return serviceDTO;
		
	}

	
	//get transaction list using serviceId
	
	@Override
	public List<ServiceTransactionResponseDTO> getServiceTransactionUsingServiceId(Long serviceId) {
		Servce service = servceDao.findById(serviceId).orElseThrow(()-> new ResourceNotFoundException("Service id is invalid"));
		List<ServiceTransactionResponseDTO> transactionList = new ArrayList<>();
		for(ServiceTransaction transaction : service.getTransactionList()) {
			transactionList.add(myMapper(transaction));
		}
		return transactionList;
		
	}
	

	public ServiceTransactionResponseDTO myMapper(ServiceTransaction transaction) {
		ServiceTransactionResponseDTO serviceTransactionResponse = new ServiceTransactionResponseDTO();
		serviceTransactionResponse.setId(transaction.getId());
		serviceTransactionResponse.setDateGenTime(transaction.getDateGenTime());
		serviceTransactionResponse.setPaymentMethod(transaction.getPaymentMethod());
		serviceTransactionResponse.setReciverUserId(transaction.getReciverUserId().getId());
		serviceTransactionResponse.setSenderUserId(transaction.getSenderUserId().getId());
		serviceTransactionResponse.setServiceAmount(transaction.getAmount());
		serviceTransactionResponse.setServiceId(transaction.getServiceId().getId());
		serviceTransactionResponse.setSuccess(true);
		return serviceTransactionResponse;
		
	}
	
	


}
