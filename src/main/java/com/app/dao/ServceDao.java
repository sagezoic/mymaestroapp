package com.app.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.app.entities.Servce;

public interface ServceDao extends JpaRepository<Servce, Long>{

	Optional<Servce> findById(Long Id);
	
}
