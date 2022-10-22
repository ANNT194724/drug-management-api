package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.LoginDto;

@RestController
@RequestMapping("api")
public class DrugController {
	@GetMapping("/drug")
	public ResponseEntity<String> simpleController(@RequestBody LoginDto loginDto){
        return new ResponseEntity<>("Welcome!", HttpStatus.OK);
    }
}
