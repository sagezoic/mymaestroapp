package com.app.service;

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
import com.app.dto.ServceRequestDTO;
import com.app.dto.ServceResponseDTO;
import com.app.entities.Servce;
import com.app.entities.Users;

import custom_exception.ResourceNotFoundException;

@Service
@Transactional
public class ServceServiceImpl implements ServceService{

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
		
		Servce ser=mapper.map(request,Servce.class);
		System.out.println(ser);
		ser.setUserId(userDao.findById(request.getUserId()).orElse(null));
		Servce persistentser=servceDao.save(ser);
		Users user=userDao.findById(request.getUserId()).orElse(null);
		if(user!=null) {
			user.addServces(persistentser);
		}
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
	
	ServceResponseDTO myMapper(Servce service) {
		ServceResponseDTO serviceDTO = new ServceResponseDTO();
		serviceDTO.setId(service.getId());
		serviceDTO.setServiceTitle(service.getServiceTitle());
		serviceDTO.setPriceToken(service.getPriceToken());
		serviceDTO.setServicetype(service.getServicetype());
		serviceDTO.setUserId(service.getUserId().getId());
		serviceDTO.setTimePeriod(service.getTimePeriod());
		serviceDTO.setDescription(service.getDescription());
		return serviceDTO;
		
	}
	

	
	


}
