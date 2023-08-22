package com.app.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.AvailableDateDao;
import com.app.dao.ServceDao;
import com.app.dto.AvailableDateRequestDTO;
import com.app.dto.AvailableDateResponseDTO;
import com.app.dto.ServceResponseDTO;
import com.app.entities.AvailableDate;
import com.app.entities.Servce;
import com.app.entities.Users;

import custom_exception.ResourceNotFoundException;


@Service
@Transactional
public class AvailableDateServiceImpl implements AvailableDateService {

	@Autowired
	private AvailableDateDao availableDateDao;
	
	@Autowired
	private ModelMapper mapper; 
	
	@Autowired
	private ServceDao servceDao;
	
	@Override
	public AvailableDateResponseDTO addDateSlot(AvailableDateRequestDTO dto) {
		Servce ser= servceDao.findById(dto.getServiceId()).orElseThrow(()->new ResourceNotFoundException("service id not valid")); 
		AvailableDate availableDate =mapper.map(dto,AvailableDate.class);
		AvailableDate persistentAvailableDate=availableDateDao.save(availableDate);
		ser.addAvailableDate(persistentAvailableDate);
		return myMapper(persistentAvailableDate);
		
	}
	
	public AvailableDateResponseDTO myMapper (AvailableDate persistentAvailableDate) {
		AvailableDateResponseDTO availableDateResponseDTO = new AvailableDateResponseDTO();
		availableDateResponseDTO.setId(persistentAvailableDate.getId());
		availableDateResponseDTO.setFirstDate(persistentAvailableDate.getFirstDate());
		availableDateResponseDTO.setSecondDate(persistentAvailableDate.getSecondDate());
		availableDateResponseDTO.setThirdDate(persistentAvailableDate.getThirdDate());
		availableDateResponseDTO.setFourthDate(persistentAvailableDate.getFourthDate());
		availableDateResponseDTO.setFifthDate(persistentAvailableDate.getFifthDate());
		availableDateResponseDTO.setSixthDate(persistentAvailableDate.getSixthDate());
		availableDateResponseDTO.setSeventhDate(persistentAvailableDate.getSeventhDate());
		availableDateResponseDTO.setServiceId(persistentAvailableDate.getServiceId().getId());
				
		return availableDateResponseDTO;
	}
	
	@Override
	public List<AvailableDateResponseDTO> getAllAvailableDate(Long serviceId) {
		Servce service = servceDao.findById(serviceId).orElseThrow(()->new ResourceNotFoundException("Service ID is Invalid"));
		List<AvailableDateResponseDTO> dateList = new ArrayList<>();
		for(AvailableDate date : service.getDateList()) {
			dateList.add(myMapper(date));
		}
		
		return dateList;
	}
	
	@Override
	public void deleteAvailableDate(Long serviceId, Long availableDateId) {
		Servce service = servceDao.findById(serviceId).orElseThrow(()->new ResourceNotFoundException("service id is invalid"));
		AvailableDate availableDate = availableDateDao.findById(availableDateId).orElseThrow(()->new ResourceNotFoundException("AvailableDate id is invalid"));
		availableDateDao.deleteById(availableDateId);
		service.removeAvailableDate(availableDate);
	}
	
	
	@Override
	public AvailableDateResponseDTO editDateSolt(AvailableDateRequestDTO dto) {
		Servce service = servceDao.findById(dto.getServiceId()).orElseThrow(()->new ResourceNotFoundException("service id is invalid"));
		AvailableDate availableDate = mapper.map(dto, AvailableDate.class);
		availableDate.setServiceId(servceDao.findById(dto.getServiceId()).orElseThrow(()->new ResourceNotFoundException("service id is invalid")));
		AvailableDate persistanceAvailableDate = availableDateDao.save(availableDate);
		return myMapper(persistanceAvailableDate);
	}
	
}
