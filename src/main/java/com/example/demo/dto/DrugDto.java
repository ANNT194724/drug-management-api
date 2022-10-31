package com.example.demo.dto;


import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@Data
public class DrugDto {
	@NotBlank
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
	private String contraindication;
	private String precaution;
	private String overdose;
	private String concentration;
	@NotEmpty
	private List<DrugUnitPriceDto> units;
	@PositiveOrZero
	private Integer vatPercent;
	private String note;
}
