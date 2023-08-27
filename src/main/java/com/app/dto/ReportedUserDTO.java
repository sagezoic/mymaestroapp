package com.app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReportedUserDTO {
	
	private Long id;
	
	private boolean removedStatus;
	
	private Long reportingUserId;
	
	private Long reportedUserId;
	
	private String description;

}
