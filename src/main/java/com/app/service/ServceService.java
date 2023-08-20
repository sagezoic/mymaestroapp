package com.app.service;

import com.app.dto.ServceResponseDTO;
import com.app.entities.Servce;

import java.util.List;

import com.app.dto.CommonResponse;
import com.app.dto.ServceRequestDTO;

public interface ServceService {

	ServceResponseDTO addNewService(ServceRequestDTO request);
	void deleteServce(Long servceId);
	ServceResponseDTO editService(ServceRequestDTO request);
	List<Servce> getUserService(Long userId);
	 //ServceResponseDTO getUserService (Long userId);
}
