package com.app.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AvailableDateRequestDTO {

	private Long id;
	
	private LocalDateTime firstDate;
	
	private LocalDateTime secondDate;
	
	private LocalDateTime thirdDate;
	
	private LocalDateTime fourthDate;
	
	private LocalDateTime fifthDate;
	
	private LocalDateTime sixthDate;
	
	private LocalDateTime seventhDate;
	
	private Long serviceId;
	
}
