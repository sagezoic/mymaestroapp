package com.app.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.stereotype.Service;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="user_table")
@NoArgsConstructor
@Getter
@Setter
//@ToString(exclude="password")
public class Users extends BaseEntity {
	@Enumerated(EnumType.STRING)
	@Column(length=30)
	private UserRole userRole;
	@Column(length=30,unique=true)
	private String userName;
	@Column(length=30,unique=true)
	private String email;
	@Column(name="password",length=500)
	private String password;
	@Column(name="first_name",length=30)
	private String firstName;
	@Column(name="last_name",length=30)
	private String lastName;
	@Column(name="dob")
	private LocalDate dob;
	@Column(name="otp_verified")
	private boolean otpVerified;
	@Column(name="admin_verified")
	private boolean adminVerified;
	@Column(length=100,name="dp_url")
	private String dpUrl;
	@Enumerated(EnumType.STRING)
	@Column(length=30)
	private UserInterest interest;
	@Column(length=30)
	private String bio;
	@Column(name="social_media_link",length=100)
	private String socialMediaLink;
	@Column(name="token")
	private int token;
	@Column(name="enabled")
	private boolean enabled;
	@Column(name="createdAt")
	private Date createdAt;

	  @OneToMany(mappedBy="users",cascade = CascadeType.ALL, orphanRemoval = true)
	  private List<Servce> servces=new ArrayList<>();


	@Override
	public String toString() {
		return "Users [userRole=" + userRole + ", userName=" + userName + ", email=" + email + ", firstName="
				+ firstName + ", lastName=" + lastName + ", dob=" + dob + ", otpVerified=" + otpVerified
				+ ", adminVerified=" + adminVerified + ", dpUrl=" + dpUrl + ", interest=" + interest + ", bio=" + bio
				+ ", socialMediaLink=" + socialMediaLink + ", token=" + token + ", enabled=" + enabled + ", createdAt="
				+ createdAt + "]";
	}
	 
	
}
