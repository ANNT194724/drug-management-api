package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Drug;

public interface DrugRepository extends JpaRepository<Drug, Integer>{
	Drug findDrugByDrugId(Integer drugId);
}
