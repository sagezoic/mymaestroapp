package com.app.dto;

import java.util.List;

import com.app.entities.Servce;
import com.app.entities.ServiceCategory;
import com.app.entities.ServiceType;
import com.app.entities.UserRole;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@NoArgsConstructor
public class ServceRequestDTO {

	private Long id;
	private String serviceTitle; 
	private ServiceType servicetype;
	private float priceToken;
	private Long userId;
	private ServiceCategory serviceCategory;
	
}
