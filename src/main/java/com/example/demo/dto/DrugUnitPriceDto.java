package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;

@Data
public class DrugUnitPriceDto {
	@NotBlank
	@JsonProperty("unit_name")
	private String unitName;
	@PositiveOrZero
	@JsonProperty("unit_qty")
	private Integer unitQty;
	@PositiveOrZero
	@JsonProperty("max_price")
	private Long maxPrice;
	@PositiveOrZero
	private Long price;
	@PositiveOrZero
	@JsonProperty("price_before_vat")
	private Long priceBeforeVat;
	@PositiveOrZero
	@JsonProperty("wholesale_price")
	private Long wholesalePrice;
	@PositiveOrZero
	@JsonProperty("wholesale_price_before_vat")
	private Long wholesalePriceBeforeVat;
}
