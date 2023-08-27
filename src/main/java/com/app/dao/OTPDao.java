package com.app.dao;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.app.entities.OTPRecord;

@Repository
public interface OTPDao extends JpaRepository<OTPRecord, Long> {

    Optional<OTPRecord> findLatestValidOTPByEmail(String email);
  //SELECT * FROM otp_record WHERE email = :email AND timestamp > CURRENT_TIMESTAMP ORDER BY timestamp DESC LIMIT 1
    
   
}
