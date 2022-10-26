package com.example.demo.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "drug")
public class Drug {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer drugId;
	private String drugName;
	private String drugAlias;
	private String licenseCode;
	private String barcode;
	private String providerName;
	private String companyName;
	private String country;
	private String activeIngredient;
	private String drugKind;
	private String description;
	private String directionForUse;
	private String dosage;
	private String indication;
	private String adverseReaction;
	private String contradication;
	private String precaution;
	private String overdosage;
	private String concentration;
	private Integer vatPercent;
	private String note;
	private Date createdDate;
	private Date updatedDate;
	private Integer updatedUser;
}
