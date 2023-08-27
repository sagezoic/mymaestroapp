package com.app.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
	private float token;
	
	@Column(name="enabled")
	private boolean enabled;
	
	@Column(name="created_at")
	private LocalDateTime createdAt;
	
	@Column(name="mobile_no",length =10)
	private String mobileNo;

	@OneToMany(mappedBy = "userId",cascade = CascadeType.ALL,orphanRemoval = true)
	private List<LikePost> likePost = new ArrayList<>();
	
	@OneToMany(mappedBy="userId",cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Servce> servces=new ArrayList<>();
	
	@OneToMany(mappedBy ="userId",cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Post> post = new ArrayList<>();
	
	@OneToMany(mappedBy="senderUserId",cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ServiceTransaction> explorerTransactionList = new ArrayList<>();
	
	@OneToMany(mappedBy="reciverUserId",cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ServiceTransaction> maestroTransactionList = new ArrayList<>();
	
	@OneToMany(mappedBy="reportingUserId",cascade=CascadeType.ALL,orphanRemoval=true)
	private List<ReportedPost> reportedPostList=new ArrayList<>();
	
	@OneToMany(mappedBy = "followingId",cascade = CascadeType.ALL,orphanRemoval = true )
	private List<Following> followingList = new ArrayList<>();
	
	@OneToMany(mappedBy = "followersId",cascade = CascadeType.ALL, orphanRemoval = true)
	private	List<Followers> followersList = new ArrayList<>(); 

	@OneToMany(mappedBy="reportedUserId",cascade=CascadeType.ALL,orphanRemoval=true)
	private List<ReportedUser> reportedUserList=new ArrayList<>();

	
	@Override
	public String toString() {
		return "Users [userRole=" + userRole + ", userName=" + userName + ", email=" + email + ", firstName="
				+ firstName + ", lastName=" + lastName + ", dob=" + dob + ", otpVerified=" + otpVerified
				+ ", adminVerified=" + adminVerified + ", dpUrl=" + dpUrl + ", interest=" + interest + ", bio=" + bio
				+ ", socialMediaLink=" + socialMediaLink + ", token=" + token + ", enabled=" + enabled + ", createdAt="
				+ createdAt + "]";
	}
	 
	public void addServces(Servce s)
	{
		servces.add(s);
		s.setUserId(this);
	}
	public void removeServce(Servce s)
	{
		servces.remove(s);
		s.setUserId(null);
	}
	
	public void addpost(Post p)
	{
		post.add(p);
		p.setUserId(this);
	}
	public void removePost(Post p)
	{
		post.remove(p);
		p.setUserId(null);
	}
	
	public void addExplorerTransaction(ServiceTransaction st) {
		explorerTransactionList.add(st);
		st.setSenderUserId(this);
	}

	public void removeExplorerTransaction(ServiceTransaction st) {
		explorerTransactionList.remove(st);
		st.setSenderUserId(null);
	}
	
	public void addMaestroTransaction(ServiceTransaction st) {
		maestroTransactionList.add(st);
		st.setReciverUserId(this);
	}

	public void removeMaestroTransaction(ServiceTransaction st) {
		maestroTransactionList.remove(st);
		st.setReciverUserId(null);
	}
	
	public void addLikePost(LikePost lp) {
		likePost.add(lp);
		lp.setUserId(this);
	}
		
	public void removeLikePost(LikePost lp) {
		likePost.remove(lp);
		lp.setUserId(null);
	}
	
	public void addReportedPost(ReportedPost rp) {
		reportedPostList.add(rp);
		rp.setReportingUserId(this);
	}
		
	public void removeReportedPost(ReportedPost rp) {
		reportedPostList.remove(rp);
		rp.setReportingUserId(null);
	}
	
	public void addReportedUser(ReportedUser ru) {
		reportedUserList.add(ru);
		//ru.setReportedUserId(this);
	
	}
		
	public void removeReportedUser(ReportedUser ru) {
		reportedUserList.remove(ru);
		ru.setReportedUserId(null);
	}
	
	
	public boolean getAdminVerified()
	{
		return this.adminVerified;
	}
//	public UserInterest getInterest()
//	{
//		return this.interest;
//	}
	public boolean getEnabled()
	{
		return this.enabled;
	}
	
	public void addFollowers(Followers f) {
		
		followersList.add(f);
		f.setFollowersId(this);
	}
	
	public void removeFollowers(Followers f) {
		followersList.remove(f);
		f.setFollowersId(null);
		
	}
	
public void addFollowing(Following f) {
		
		followingList.add(f);
		f.setFollowingId(this);
	}
	
	public void removeFollowing(Following f) {
		followingList.remove(f);
		f.setFollowingId(null);
		
	}
	
}
