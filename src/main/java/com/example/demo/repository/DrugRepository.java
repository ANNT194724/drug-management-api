package com.example.demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Drug;

public interface DrugRepository extends JpaRepository<Drug, Integer>{
	Drug findDrugByDrugId(Integer drugId);

	Page<Drug> findAll(Pageable pageable);
}
