
  package com.app.entities;
  
  import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter; 
import lombok.NoArgsConstructor; 
import lombok.Setter;
import lombok.ToString;
  
  @Entity
  @Table(name="service_table")
  @NoArgsConstructor  
  @Getter  
  @Setter  
//  @ToString 
  public class Servce extends BaseEntity{
	  
  @Column(name="service_title",length=100)
  private String serviceTitle; 
 
  @Enumerated(EnumType.STRING)
  @Column(name="service_type")
  private ServiceType servicetype;
  
  @Column(name="price_token")
  private float priceToken;
  
  @Column(name="time_period")
  private Integer timePeriod;
  
  @Column(name="description")
  private String description;
  
  @Enumerated(EnumType.STRING)
  @Column(name="service_category")
  private ServiceCategory serviceCategory;
    
  @ManyToOne
  @JoinColumn(name="user_id")
  private Users userId;
  
  @OneToMany(mappedBy ="serviceId",cascade = CascadeType.ALL,orphanRemoval = true)
  private List<ServiceRequest> serviceList=new ArrayList<>();
  
  @OneToMany(mappedBy="serviceId",cascade = CascadeType.ALL,orphanRemoval= true)
  private List<AvailableDate> dateList=new ArrayList<>();
//  @Column(name="avb_time")
//  private List<String> avalaibleTime;

  
  
  public void addAvailableDate(AvailableDate a)
	{
		dateList.add(a);
		a.setServiceId(this);
	}
	public void removeAvailableDate(AvailableDate a)
	{
		dateList.remove(a);
		a.setServiceId(null);
	}
	
	public void addServiceRequest(ServiceRequest sr)
	{
		serviceList.add(sr);
		sr.setServiceId(this);
	}
	public void removeServce(ServiceRequest sr)
	{
		serviceList.remove(sr);
		sr.setServiceId(null);
	}
  
	@Override
	public String toString() {
		return "Service [serviceTitle=" + serviceTitle + ", servicetype=" + servicetype + ", priceToken=" + priceToken
				+ "]";
	}
  
  
  }
 