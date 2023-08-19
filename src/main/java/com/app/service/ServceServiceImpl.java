package com.app.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.ServceDao;
import com.app.dao.UserDao;
import com.app.dto.CommonResponse;
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
	
	@Override
	public ServceResponseDTO addNewService(ServceRequestDTO request) {
		
		//Servce servce=new Servce();
		Servce ser=mapper.map(request,Servce.class);
		System.out.println(ser);
	ser.setUserId(userDao.findById(request.getUserId()).orElse(null));
		//Long i=request.getUserId();
		//ser.setUserId(i);
		Servce persistentser=servceDao.save(ser);
		Users user=userDao.findById(request.getUserId()).orElse(null);
		if(user!=null) {
			user.addServces(persistentser);
		}
		return mapper.map(persistentser,ServceResponseDTO.class);
	}

	@Override
	public ServceResponseDTO editService(ServceRequestDTO request) {
			
//		Optional<Servce> ser=servceDao.findById(request.getId());
//		if(ser.isPresent())
//		{
//			Servce ser1=mapper.map(request,Servce.class);
//			Servce persistentser=servceDao.save(ser1);
//		}
		Servce ser=mapper.map(request, Servce.class);
		System.out.println("ser");
		Servce persistentser=servceDao.save(ser);
		
		return mapper.map(persistentser, ServceResponseDTO.class);
	}

	
	@Override
	public void deleteServce(Long servceId) {
		// TODO Auto-generated method stub
		System.out.println("servceId " + servceId);
		Optional<Servce> servce=servceDao.findById(servceId);
		if(servce.isPresent())
		{
			servceDao.deleteById(servceId);
			System.out.println("inside delete service if statement " );
		}
	}
	
	@Override
	public List<Servce> /*ServceResponseDTO*/ getUserService(Long userId) {
		
		List<Servce> service=servceDao.findByUserIdWithJoinFetch(userId);
		//List<Servce> service = servceDao.findByuserId(userDao.findById(userId).orElse(null));
	//int count=service.size();
		System.out.println(service);
		System.out.println("hello");
//	    ServceResponseDTO ser = new ServceResponseDTO();
//	    ser.setServiceList(service);
//	    System.out.println(ser.getServiceList());
		return service; 
	    //return mapper.map(ser, ServceResponseDTO.class);
	}

}
