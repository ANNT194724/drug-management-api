package com.example.demo.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;

@Data
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
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
