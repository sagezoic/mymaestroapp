package com.app.service;

import com.app.dto.ServceResponseDTO;
import com.app.entities.Servce;
import com.app.entities.Users;

import java.util.List;

import com.app.dto.CommonResponse;
import com.app.dto.ServceRequestDTO;

public interface ServceService {

	ServceResponseDTO addNewService(ServceRequestDTO request);
//	void deleteServce(Long servceId);
	ServceResponseDTO editService(ServceRequestDTO request);
	List<Servce> getUserService(Users userId);
	//void delServce(Long userId,Long servceId);
	 //ServceResponseDTO getUserService (Long userId);
	public String deleteServceDetails(Long UserId, Long serviceId);
}
