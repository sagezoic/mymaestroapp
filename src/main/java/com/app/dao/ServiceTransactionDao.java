package com.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.ServiceRequest;
import com.app.entities.ServiceTransaction;

public interface ServiceTransactionDao extends JpaRepository<ServiceTransaction, String> {

	
}
