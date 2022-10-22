package com.example.demo.controller;

import java.sql.Date;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.LoginDto;
import com.example.demo.dto.RegisterDto;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @PostMapping("/login")
    public ResponseEntity<String> authenticateUser(@RequestBody LoginDto loginDto){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                (loginDto.getEmail() == null) ? loginDto.getPhonenumber() : loginDto.getEmail(), loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new ResponseEntity<>("User logged in successfully!.", HttpStatus.OK);
    }
    
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterDto registerDto){
    	if(userRepository.existsByPhonenumber(registerDto.getPhonenumber())) {
            return new ResponseEntity<>("This phone number is already registered!", HttpStatus.BAD_REQUEST);
        }
    	
    	if(userRepository.existsByEmail(registerDto.getEmail())) {
    		return new ResponseEntity<>("This email is already registered!", HttpStatus.BAD_REQUEST);
    	}
    	
    	if(!registerDto.getPhonenumber().matches("^[0-9]+$")) {
    		return new ResponseEntity<>("Invalid phone number!", HttpStatus.BAD_REQUEST);
    	}
    	
    	if(!registerDto.getEmail().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
    		return new ResponseEntity<>("Invalid email!", HttpStatus.BAD_REQUEST);
    	}
    	
    	if(registerDto.getPassword().length() < 8) {
    		return new ResponseEntity<>("Password is too short!", HttpStatus.BAD_REQUEST);
    	}
    	
    	User user = new User();
        user.setName(registerDto.getName());
        user.setPhonenumber(registerDto.getPhonenumber());
        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        user.setAddress(registerDto.getAddress());
        user.setCreatedDate(Date.valueOf(LocalDate.now()));
        
        userRepository.save(user);

        return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
    }
}
