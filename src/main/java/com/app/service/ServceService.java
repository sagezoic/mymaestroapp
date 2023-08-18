package com.app.service;

import com.app.dto.ServceResponseDTO;
import com.app.dto.ServceRequestDTO;

public interface ServceService {

	ServceResponseDTO addNewService(ServceRequestDTO request);
	void deleteServce(Long servceId);
	ServceResponseDTO editService(ServceRequestDTO request);

}
