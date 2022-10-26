package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.DrugUnit;
import com.example.demo.repository.DrugUnitRepository;

@Service
@Transactional
public class DrugUnitService {
	@Autowired
	private DrugUnitRepository drugUnitRepository;
	     
    public List<DrugUnit> viewAll() {
        return drugUnitRepository.findAll();
    }
     
    public void save(DrugUnit drugUnit) {
        drugUnitRepository.save(drugUnit);
    }
     
    public void deleteByDrugId(Integer id) {
        drugUnitRepository.deleteByDrugId(id);
    }
	
    public Boolean existById(Integer id) {
    	return drugUnitRepository.existsById(id);
    }
}
