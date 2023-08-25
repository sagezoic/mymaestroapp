package com.app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@NoArgsConstructor
@Getter
@Setter
@ToString
@AllArgsConstructor
public class ReportedPostDTO{
	
	private Long id;
	
	private Long postId;
	
	private boolean removedStatus;
	
	private Long reportingUserId;

	
	
}
