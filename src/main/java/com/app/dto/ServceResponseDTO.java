package com.app.dto;

import com.app.entities.ServiceType;
import com.app.entities.UserRole;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ServceResponseDTO {

	private Long id;
	private String serviceTitle; 
	  private ServiceType servicetype;
	  private float priceToken;
	  private Long user_id;
	  
	  
}
