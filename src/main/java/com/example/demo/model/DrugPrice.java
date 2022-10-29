package com.example.demo.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.PositiveOrZero;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "drug_price")
public class DrugPrice {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer drugPriceId;
	private Integer drugId;
	private Integer drugUnitId;
	private String drugName;
	@PositiveOrZero
	private Long priceBeforeVat;
	@PositiveOrZero
	private Long price;
	@PositiveOrZero
	private Long wholesalePrice;
	@PositiveOrZero
	private Long wholesalePriceBeforeVat;
	private String unitName;
	private Date createdDate;
	private Date updatedDate;
	private Integer updatedUser;
}
