package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Drug;
import com.example.demo.repository.DrugRepository;

@Service
@Transactional(readOnly = true)
public class DrugService {
	@Autowired
	private DrugRepository drugRepository;
	     
    public Page<Drug> viewAll() {
        Pageable pageRequest = PageRequest.of(0, 2, Sort.by("drugName"));
        return drugRepository.findAll(pageRequest);
    }

    @Transactional
    public void save(Drug drug) {
        drugRepository.save(drug);
    }
     
    public Drug getDrugById(Integer drugId) {
        return drugRepository.findDrugByDrugId(drugId);
    }

    @Transactional
    public void deleteById(Integer id) {
        drugRepository.deleteById(id);
    }
	
    public Boolean existById(Integer id) {
    	return drugRepository.existsById(id);
    }
}
