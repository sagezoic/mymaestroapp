package com.app.entities;

import javax.persistence.Entity;
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
@ToString
public class Service extends BaseEntity{

	private String serviceTitle;
	private ServiceType servicetype;
	private float priceToken;
}
