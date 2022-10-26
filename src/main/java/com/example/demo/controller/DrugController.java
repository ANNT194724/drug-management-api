package com.example.demo.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.DrugDto;
import com.example.demo.dto.DrugUnitPriceDto;
import com.example.demo.dto.PriceView;
import com.example.demo.model.Drug;
import com.example.demo.model.DrugPrice;
import com.example.demo.model.DrugUnit;
import com.example.demo.service.DrugPriceService;
import com.example.demo.service.DrugService;
import com.example.demo.service.DrugUnitService;

@RestController
@RequestMapping("api/drugs")
public class DrugController {
	
	@Autowired
	private DrugService drugService;
	
	@Autowired
	private DrugUnitService unitService;
	
	@Autowired
	private DrugPriceService priceService;
	
	@GetMapping
	public List<Drug> viewAll(){
		return drugService.viewAll();
    }
	
	@PostMapping
	public ResponseEntity<?> addDrug(@RequestBody DrugDto drugDto) {
		Drug drug = new Drug();
		drug.setDrugName(drugDto.getDrugName());
		drug.setDrugAlias(drugDto.getDrugAlias());
		drug.setLicenseCode(drugDto.getLicenseCode());
		drug.setBarcode(drugDto.getBarcode());
		drug.setProviderName(drugDto.getProviderName());
		drug.setCompanyName(drugDto.getCompanyName());
		drug.setCountry(drugDto.getCountry());
		drug.setActiveIngredient(drugDto.getActiveIngredient());
		drug.setDrugKind(drugDto.getDrugKind());
		drug.setDescription(drugDto.getDescription());
		drug.setDirectionForUse(drugDto.getDirectionForUse());
		drug.setDosage(drugDto.getDosage());
		drug.setIndication(drugDto.getIndication());
		drug.setAdverseReaction(drugDto.getAdverseReaction());
		drug.setContradication(drugDto.getContradication());
		drug.setPrecaution(drugDto.getPrecaution());
		drug.setOverdosage(drugDto.getOverdosage());
		drug.setConcentration(drugDto.getConcentration());
		drug.setVatPercent(drugDto.getVatPercent());
		drug.setNote(drugDto.getNote());
		drug.setCreatedDate(Date.valueOf(LocalDate.now()));
//		drug.setUpdatedUser(((CustomUserDetails)principal).getUser().getUserId());
		
		drugService.save(drug);
		return new ResponseEntity<>("New drug added successfully", HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> viewById(@PathVariable Integer id) {
		if(!drugService.existById(id)) {
			return new ResponseEntity<>("Drug not found", HttpStatus.NOT_FOUND);
		} else {
			Drug drug = drugService.getById(id);
			return new ResponseEntity<>(drug, HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateById(@RequestBody DrugDto drugDto, @PathVariable Integer id) {
		
		if(!drugService.existById(id)) {
			return new ResponseEntity<>("Drug not found", HttpStatus.NOT_FOUND);
		}
		Drug drug = drugService.getById(id);
		drug.setDrugName(drugDto.getDrugName());
		drug.setDrugAlias(drugDto.getDrugAlias());
		drug.setLicenseCode(drugDto.getLicenseCode());
		drug.setBarcode(drugDto.getBarcode());
		drug.setProviderName(drugDto.getProviderName());
		drug.setCompanyName(drugDto.getCompanyName());
		drug.setCountry(drugDto.getCountry());
		drug.setActiveIngredient(drugDto.getActiveIngredient());
		drug.setDrugKind(drugDto.getDrugKind());
		drug.setDescription(drugDto.getDescription());
		drug.setDirectionForUse(drugDto.getDirectionForUse());
		drug.setDosage(drugDto.getDosage());
		drug.setIndication(drugDto.getIndication());
		drug.setAdverseReaction(drugDto.getAdverseReaction());
		drug.setContradication(drugDto.getContradication());
		drug.setPrecaution(drugDto.getPrecaution());
		drug.setOverdosage(drugDto.getOverdosage());
		drug.setConcentration(drugDto.getConcentration());
		drug.setVatPercent(drugDto.getVatPercent());
		drug.setNote(drugDto.getNote());
		drug.setUpdatedDate(Date.valueOf(LocalDate.now()));	
//		drug.setUpdatedUser(((CustomUserDetails)principal).getUser().getUserId());
		
		drugService.save(drug);
		return new ResponseEntity<>("Updated successfully", HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Integer id) {
		
		if(!drugService.existById(id)) {
			return new ResponseEntity<>("Drug not found", HttpStatus.NOT_FOUND);
		}
		priceService.deleteByDrugId(id);
		unitService.deleteByDrugId(id);
		drugService.deleteById(id);
		return new ResponseEntity<>("Deleted successfully", HttpStatus.OK);
	}
	
	@PostMapping("/{id}/price")
	public ResponseEntity<?> addPrice(@PathVariable Integer id, @RequestBody DrugUnitPriceDto dto) {
		if(!drugService.existById(id)) {
			return new ResponseEntity<>("Drug not found", HttpStatus.NOT_FOUND);
		}
		DrugUnit drugUnit = new DrugUnit();
		Drug drug = drugService.getById(id);
		drugUnit.setUnitName(dto.getUnitName());
		drugUnit.setUnitQty(dto.getUnitQty());
		drugUnit.setMaxPrice(dto.getMaxPrice());
		drugUnit.setDrugId(id);
		drugUnit.setDrugName(drug.getDrugName());
		drugUnit.setCreatedDate(Date.valueOf(LocalDate.now()));
		
		unitService.save(drugUnit);
		
		DrugPrice drugPrice = new DrugPrice();
		drugPrice.setPrice(dto.getPrice());
		drugPrice.setPriceBeforeVat(dto.getPriceBeforeVat());
		drugPrice.setWholesalePrice(dto.getWholesalePrice());
		drugPrice.setWholesalePriceBeforeVat(dto.getWholesalePriceBeforeVat());
		drugPrice.setDrugId(id);
		drugPrice.setDrugName(drug.getDrugName());
		drugPrice.setCreatedDate(Date.valueOf(LocalDate.now()));
		drugPrice.setUnitName(drugUnit.getUnitName());
		drugPrice.setDrugUnitId(drugUnit.getDrugUnitId());
		drugPrice.setCreatedDate(Date.valueOf(LocalDate.now()));
		
		priceService.save(drugPrice);
		return new ResponseEntity<>("Price added successfully", HttpStatus.OK);
	}
	
	@GetMapping("/{id}/price")
	public ResponseEntity<?> viewPrice(@PathVariable Integer id) {
		List<PriceView> priceView = priceService.viewPricebyDrugId(id);
		return new ResponseEntity<>(priceView, HttpStatus.OK);
	}
	
}
