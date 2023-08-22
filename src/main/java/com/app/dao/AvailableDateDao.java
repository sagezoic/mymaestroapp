package com.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.AvailableDate;

public interface AvailableDateDao extends JpaRepository<AvailableDate, Long> {

	
	
}
