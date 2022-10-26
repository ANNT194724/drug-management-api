package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.PriceView;
import com.example.demo.model.DrugPrice;
import com.example.demo.repository.DrugPriceRepository;

@Service
@Transactional
public class DrugPriceService {
	@Autowired
	private DrugPriceRepository drugPriceRepository;
	     
    public List<DrugPrice> viewAll() {
        return drugPriceRepository.findAll();
    }
     
    public void save(DrugPrice drugPrice) {
        drugPriceRepository.save(drugPrice);
    }
     
    public void deleteByDrugId(Integer id) {
        drugPriceRepository.deleteByDrugId(id);
    }
    
    public List<PriceView> viewPricebyDrugId(Integer id) {
    	return drugPriceRepository.viewPriceById(id);
    }
}
