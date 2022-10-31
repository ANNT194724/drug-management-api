package com.example.demo.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "drug_unit")
public class DrugUnit {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer drugUnitId;
	@NotBlank
	private String unitName;
	private Integer drugId;
	private String drugName;
	@PositiveOrZero
	private Integer unitQty;
	@PositiveOrZero
	private Long maxPrice;
	private Date createdDate;
	private Date updatedDate;
	private Integer updatedUser;
}
