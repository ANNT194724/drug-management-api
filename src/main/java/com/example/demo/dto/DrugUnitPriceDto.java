package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class DrugUnitPriceDto {
	@JsonProperty("unit_name")
	private String unitName;
	@JsonProperty("unit_qty")
	private Integer unitQty;
	@JsonProperty("max_price")
	private Long maxPrice;
	private Long price;
	@JsonProperty("price_before_vat")
	private Long priceBeforeVat;
	@JsonProperty("wholesale_price")
	private Long wholesalePrice;
	@JsonProperty("wholesale_price_before_vat")
	private Long wholesalePriceBeforeVat;
}
