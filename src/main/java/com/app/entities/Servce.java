
  package com.app.entities;
  
  import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn; import
  javax.persistence.ManyToOne; import javax.persistence.Table;
  
  import lombok.Getter; import lombok.NoArgsConstructor; import lombok.Setter;
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
  @Enumerated(EnumType.STRING)
  @Column(name="service_category")
  private ServiceCategory serviceCategory;
  @ManyToOne  
  @JoinColumn(name="user_id")
  private Users userId;
  
  

@Override
public String toString() {
	return "Service [serviceTitle=" + serviceTitle + ", servicetype=" + servicetype + ", priceToken=" + priceToken
			+ "]";
}
  
  
  }
 