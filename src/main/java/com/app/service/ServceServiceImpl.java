package com.app.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.ServceDao;
import com.app.dao.UserDao;
import com.app.dto.ServceRequestDTO;
import com.app.dto.ServceResponseDTO;
import com.app.entities.Servce;

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
		
		Servce ser=mapper.map(request,Servce.class);
		System.out.println(ser);
		ser.setUserId(userDao.findById(request.getUserId()).orElse(null));
		Servce persistentser=servceDao.save(ser);
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
		Optional<Servce> servce=servceDao.findById(servceId);
		if(servce.isPresent())
		{
			servceDao.deleteById(servceId);
		}
	}

}
