package com.app.service;

import java.util.List;

import com.app.dto.AvailableDateRequestDTO;
import com.app.dto.AvailableDateResponseDTO;

public interface AvailableDateService {
	
	AvailableDateResponseDTO addDateSlot(AvailableDateRequestDTO dto);
	
	List<AvailableDateResponseDTO> getAllAvailableDate(Long serviceId);

	void deleteAvailableDate(Long serviceId , Long availableDateId);
	
	AvailableDateResponseDTO editDateSolt(AvailableDateRequestDTO dto);
	
}
