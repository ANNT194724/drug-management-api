package com.example.demo.dto;

public interface PriceView {
	String getUnit_name();
	Integer getUnit_qty();
	Long getMax_price();
	Long getPrice();
	Long getPrice_before_vat();
	Long getWholesale_price();
	Long getWholesale_price_before_vat();
}
