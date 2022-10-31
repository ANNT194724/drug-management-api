package com.example.demo.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;

@Data
public class DrugUnitPriceDto {
	@NotBlank
	private String unitName;
	@PositiveOrZero
	private Integer unitQty;
	@PositiveOrZero
	private Long maxPrice;
	@PositiveOrZero
	private Long price;
	@PositiveOrZero
	private Long priceBeforeVat;
	@PositiveOrZero
	private Long wholesalePrice;
	@PositiveOrZero
	private Long wholesalePriceBeforeVat;
}
