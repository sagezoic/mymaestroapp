package com.app.dto;

import java.util.List;

import com.app.entities.Servce;
import com.app.entities.ServiceType;
import com.app.entities.UserRole;
import com.app.entities.Users;

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
	private int timePeriod;
	private String description;
	private float priceToken;
	private Long userId;
}
