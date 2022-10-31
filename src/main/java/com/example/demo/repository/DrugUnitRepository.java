package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.model.DrugUnit;

import java.util.List;

public interface DrugUnitRepository extends JpaRepository<DrugUnit, Integer>{

	List<DrugUnit> findDrugUnitsByDrugId(Integer drugId);

	@Modifying
	@Query(value = "DELETE FROM drug_unit WHERE drug_id = :id", nativeQuery = true)
	void deleteByDrugId(@Param("id") Integer id);
}
