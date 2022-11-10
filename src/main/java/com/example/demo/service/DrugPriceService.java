package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.PriceView;
import com.example.demo.model.DrugPrice;
import com.example.demo.repository.DrugPriceRepository;

@Service
@Transactional(readOnly = true)
public class DrugPriceService {
	@Autowired
	private DrugPriceRepository drugPriceRepository;

    @Transactional
    public void save(DrugPrice drugPrice) {
        drugPriceRepository.save(drugPrice);
    }

    @Transactional
    public void deleteByDrugId(Integer id) {
        drugPriceRepository.deleteByDrugId(id);
    }
    
    public List<PriceView> viewPriceByDrugId(Integer id) {
    	return drugPriceRepository.viewPriceById(id);
    }

    public DrugPrice findPriceByDrugNameAndUnitName(String drugName, String unitName) {
        return drugPriceRepository.findDrugPriceByDrugNameAndUnitName(drugName, unitName);
    }
}
