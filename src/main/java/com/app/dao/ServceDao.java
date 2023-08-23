package com.app.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.dto.ServceRequestDTO;
import com.app.entities.Servce;
import com.app.entities.Users;

public interface ServceDao extends JpaRepository<Servce, Long>{

	Optional<Servce> findById(Servce servce);
	List<Servce> findByuserId(Long userId);
	//List<Servce> findById(Long userId);
	
//	@Query("select s from service_table s join fetch s.userId where d.userId = :uid")
//	List<Servce> findByUserId(@Param("uid") Long userId);
	
	@Query("SELECT s FROM Servce s JOIN FETCH s.userId u WHERE u.id = :uid")
	List<Servce> findByUserIdWithJoinFetch(@Param("uid") Long userId);

	
}
