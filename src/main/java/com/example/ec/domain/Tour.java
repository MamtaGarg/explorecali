package com.example.ec.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Tour implements Serializable {

	private static final long serialVersionUID = 4176952834228719478L;

	@Id
	@GeneratedValue
	private Integer id;
	
	@Column
	private String title;
	
	@Column(length=2000)
	private String description;
	
	@Column(length=2000)
	private String blurb;
	
	@Column
	private Integer price;
	
	@Column
	private String duration;
	
	@Column(length=2000)
	private String bullets;
	
	@Column
	private String keywords;
	
	@ManyToOne
	private TourPackage tourPackage;
	
	@Column
	private Difficulty difficulty;
	
	@Column
	private Region region;
}
