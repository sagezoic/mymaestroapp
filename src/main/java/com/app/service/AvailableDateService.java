package com.app.service;

import com.app.dto.AvailableDateRequestDTO;
import com.app.dto.AvailableDateResponseDTO;

public interface AvailableDateService {
	
	AvailableDateResponseDTO addDateSlot(AvailableDateRequestDTO dto);
	
}
