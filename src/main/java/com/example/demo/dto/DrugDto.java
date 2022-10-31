package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@Data
public class DrugDto {
	@NotBlank
	@JsonProperty("drug_name")
	private String drugName;
	@JsonProperty("drug_alias")
	private String drugAlias;
	@JsonProperty("license_code")
	private String licenseCode;
	private String barcode;
	@JsonProperty("provider_name")
	private String providerName;
	@JsonProperty("company_name")
	private String companyName;
	private String country;
	@JsonProperty("active_ingredient")
	private String activeIngredient;
	@JsonProperty("drug_kind")
	private String drugKind;
	private String description;
	@JsonProperty("direction_for_use")
	private String directionForUse;
	private String dosage;
	private String indication;
	@JsonProperty("adverse_reaction")
	private String adverseReaction;
	private String contraindication;
	private String precaution;
	private String overdose;
	private String concentration;
	@NotEmpty
	private List<DrugUnitPriceDto> units;
	@PositiveOrZero
	@JsonProperty("vat_percent")
	private Integer vatPercent;
	private String note;
}
