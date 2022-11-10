package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.dto.PriceView;
import com.example.demo.model.DrugPrice;

public interface DrugPriceRepository extends JpaRepository<DrugPrice, Integer>{

	DrugPrice findDrugPriceByDrugNameAndUnitName(String drugName, String unitName);


	@Modifying
	@Query(value = "DELETE FROM drug_price WHERE drug_id = :id", nativeQuery = true)
	void deleteByDrugId(@Param("id") Integer id);

	@Query(value = "select u.unit_name as unitName, unit_qty as unitQty, max_price as maxPrice, price, price_before_vat " +
			"as priceBeforeVat, wholesale_price as wholesalePrice, wholesale_price_before_vat as wholesalePriceBeforeVat"
			+ " from drug_unit as u join drug_price as p on u.drug_unit_id = p.drug_unit_id where u.drug_id = :id", nativeQuery = true)
	List<PriceView> viewPriceById(@Param("id") Integer id);
}
