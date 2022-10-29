package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Drug;
import com.example.demo.repository.DrugRepository;

@Service
@Transactional(readOnly = true)
public class DrugService {
	@Autowired
	private DrugRepository drugRepository;
	     
    public List<Drug> viewAll() {
        return drugRepository.findAll();
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
