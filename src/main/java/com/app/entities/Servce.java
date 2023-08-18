
  package com.app.entities;
  
  import javax.persistence.Entity; import javax.persistence.JoinColumn; import
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
  
  private String serviceTitle; 
  private ServiceType servicetype;
  private float priceToken;
  
  @ManyToOne  
  @JoinColumn(name="user_id")
  private Users users;

@Override
public String toString() {
	return "Service [serviceTitle=" + serviceTitle + ", servicetype=" + servicetype + ", priceToken=" + priceToken
			+ "]";
}
  
  
  }
 