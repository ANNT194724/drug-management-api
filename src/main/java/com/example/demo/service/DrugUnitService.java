package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.DrugUnit;
import com.example.demo.repository.DrugUnitRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class DrugUnitService {
	@Autowired
	private DrugUnitRepository drugUnitRepository;

    @Transactional
    public void save(DrugUnit drugUnit) {
        drugUnitRepository.save(drugUnit);
    }

    @Transactional
    public void deleteByDrugId(Integer id) {
        drugUnitRepository.deleteByDrugId(id);
    }
}
