package com.app.dao;

import com.app.entities.ServiceRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRequestDao extends JpaRepository<ServiceRequest,Long> {

}
