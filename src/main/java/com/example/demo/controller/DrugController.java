package com.example.demo.controller;

import java.security.Principal;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class DrugController {
	@GetMapping("/drug")
	public ResponseEntity<String> simpleController(Principal principal){
        return new ResponseEntity<>("Welcome " + principal.getName() , HttpStatus.OK);
    }
}
