package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.dto.PriceView;
import com.example.demo.model.DrugPrice;

public interface DrugPriceRepository extends JpaRepository<DrugPrice, Integer>{
	@Modifying
	@Query(value = "DELETE FROM drug_price WHERE drug_id = :id", nativeQuery = true)
	void deleteByDrugId(@Param("id") Integer id);

	@Query(value = "select u.unit_name, unit_qty, max_price, price, price_before_vat, wholesale_price, wholesale_price_before_vat "
			+ "from drug_unit as u join drug_price as p on u.drug_unit_id = p.drug_unit_id where u.drug_id = :id", nativeQuery = true)
	List<PriceView> viewPriceById(@Param("id") Integer id);
}
