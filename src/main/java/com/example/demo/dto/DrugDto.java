package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class DrugDto {
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
	private String contradication;
	private String precaution;
	private String overdosage;
	private String concentration;
	@JsonProperty("vat_percent")
	private Integer vatPercent;
	private String note;
}
