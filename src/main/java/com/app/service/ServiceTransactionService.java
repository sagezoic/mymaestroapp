package com.app.service;

import com.app.dto.ServiceTransactionRequestDTO;
import com.app.dto.ServiceTransactionResponseDTO;

public interface ServiceTransactionService {

	public void addTransaction(ServiceTransactionRequestDTO dto);
}
