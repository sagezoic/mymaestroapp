package com.app.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter	
public class CommonResponse {
	private String status;
	private Object data;
	private LocalDateTime timestamp;
	public CommonResponse(String status, Object data) {
		super();
		this.status = status;
		this.data = data;
		this.timestamp=LocalDateTime.now();
	}
}
