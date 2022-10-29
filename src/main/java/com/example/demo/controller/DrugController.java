package com.example.demo.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.DrugDto;
import com.example.demo.dto.DrugUnitPriceDto;
import com.example.demo.dto.PriceView;
import com.example.demo.jwt.JwtTokenProvider;
import com.example.demo.model.Drug;
import com.example.demo.model.DrugPrice;
import com.example.demo.model.DrugUnit;
import com.example.demo.service.DrugPriceService;
import com.example.demo.service.DrugService;
import com.example.demo.service.DrugUnitService;

import javax.validation.Valid;

@RestController
@RequestMapping("api/drugs")
public class DrugController {
	@Autowired
	private DrugService drugService;
	
	@Autowired
	private DrugUnitService unitService;
	
	@Autowired
	private DrugPriceService priceService;
	
	@Autowired 
	private JwtTokenProvider jwtProvider;
	
	@GetMapping
	public List<Drug> viewAll(){
		return drugService.viewAll();
    }
	
	@PostMapping
	public ResponseEntity<?> addDrug(@RequestHeader("Authorization") String jwt, @Valid @RequestBody DrugDto drugDto) {
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
		drug.setContraindication(drugDto.getContraindication());
		drug.setPrecaution(drugDto.getPrecaution());
		drug.setOverdose(drugDto.getOverdose());
		drug.setConcentration(drugDto.getConcentration());
		drug.setVatPercent(drugDto.getVatPercent());
		drug.setNote(drugDto.getNote());
		drug.setCreatedDate(Date.valueOf(LocalDate.now()));
		drug.setUpdatedUser(jwtProvider.getUserIdFromJWT(jwt.substring(7)));
		
		drugService.save(drug);
		return new ResponseEntity<>("New drug added successfully", HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> viewById(@PathVariable Integer id) {
		if(!drugService.existById(id)) {
			return new ResponseEntity<>("Drug not found", HttpStatus.NOT_FOUND);
		}
		Drug drug = drugService.getDrugById(id);
		return new ResponseEntity<>(drug, HttpStatus.NOT_FOUND);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateById(@PathVariable Integer id, @RequestHeader("Authorization") String jwt, @RequestBody DrugDto drugDto) {
		
		if(!drugService.existById(id)) {
			return new ResponseEntity<>("Drug not found", HttpStatus.NOT_FOUND);
		}
		Drug drug = drugService.getDrugById(id);
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
		drug.setContraindication(drugDto.getContraindication());
		drug.setPrecaution(drugDto.getPrecaution());
		drug.setOverdose(drugDto.getOverdose());
		drug.setConcentration(drugDto.getConcentration());
		drug.setVatPercent(drugDto.getVatPercent());
		drug.setNote(drugDto.getNote());
		drug.setUpdatedDate(Date.valueOf(LocalDate.now()));	
		drug.setUpdatedUser(jwtProvider.getUserIdFromJWT(jwt.substring(7)));
		
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

	@Transactional
	@PostMapping("/{id}/price")
	public ResponseEntity<?> addPrice(@PathVariable Integer id, @RequestHeader("Authorization") String jwt, @Valid @RequestBody DrugUnitPriceDto dto) {
		if(!drugService.existById(id)) {
			return new ResponseEntity<>("Drug not found", HttpStatus.NOT_FOUND);
		}
		DrugUnit drugUnit = new DrugUnit();
		Drug drug = drugService.getDrugById(id);
		drugUnit.setUnitName(dto.getUnitName());
		drugUnit.setUnitQty(dto.getUnitQty());
		drugUnit.setMaxPrice(dto.getMaxPrice());
		drugUnit.setDrugId(id);
		drugUnit.setDrugName(drug.getDrugName());
		drugUnit.setCreatedDate(Date.valueOf(LocalDate.now()));
		drugUnit.setUpdatedUser(jwtProvider.getUserIdFromJWT(jwt.substring(7)));
		
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
		drugPrice.setUpdatedUser(jwtProvider.getUserIdFromJWT(jwt.substring(7)));
		
		priceService.save(drugPrice);
		return new ResponseEntity<>("Price added successfully", HttpStatus.OK);
	}
	
	@GetMapping("/{id}/price")
	public ResponseEntity<?> viewPrice(@PathVariable Integer id) {
		if(!drugService.existById(id)) {
			return new ResponseEntity<>("Drug not found", HttpStatus.NOT_FOUND);
		}
		List<PriceView> priceView = priceService.viewPriceByDrugId(id);
		return new ResponseEntity<>(priceView, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}/price")
	public ResponseEntity<?> deletePrice(@PathVariable Integer id) {
		if(!drugService.existById(id)) {
			return new ResponseEntity<>("Drug not found", HttpStatus.NOT_FOUND);
		}
		priceService.deleteByDrugId(id);
		unitService.deleteByDrugId(id);
		return new ResponseEntity<>("Deleted successfully", HttpStatus.OK);
	}
}
