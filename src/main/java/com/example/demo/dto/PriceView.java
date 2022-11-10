package com.example.demo.dto;

public interface PriceView {
	String getUnitName();
	Integer getUnitQty();
	Long getMaxPrice();
	Long getPrice();
	Long getPriceBeforeVat();
	Long getWholesalePrice();
	Long getWholesalePriceBeforeVat();
}
