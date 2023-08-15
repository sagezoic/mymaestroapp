
package com.app.dto;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.servlet.http.Cookie;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//emp id , full name , salary , join date
@Getter
@Setter
@ToString
public class AuthResp {

	private Long id;
	private String firstName;
	private String lastName;
	//private String sessionCookie;
}
