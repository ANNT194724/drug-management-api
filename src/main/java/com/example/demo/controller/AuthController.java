package com.example.demo.controller;

import java.sql.Date;
import java.time.LocalDate;

import com.example.demo.model.Role;
import com.example.demo.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.LoginDto;
import com.example.demo.dto.RegisterDto;
import com.example.demo.jwt.JwtTokenProvider;
import com.example.demo.model.CustomUserDetails;
import com.example.demo.model.User;
import com.example.demo.service.CustomUserDetailsService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    RoleRepository roleRepository;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginDto loginDto){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getPhonenumberOrEmail(), loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.generateToken((CustomUserDetails) authentication.getPrincipal());
        return new ResponseEntity<>("access_token: " + jwt, HttpStatus.OK);
    }

    @Transactional
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterDto registerDto){
    	if(userService.existsByPhonenumber(registerDto.getPhonenumber())) {
            return new ResponseEntity<>("This phone number is already registered!", HttpStatus.BAD_REQUEST);
        }
    	
    	if(userService.existsByEmail(registerDto.getEmail())) {
    		return new ResponseEntity<>("This email is already registered!", HttpStatus.BAD_REQUEST);
    	}

    	if(!registerDto.getPhonenumber().matches("^[0-9]+$")) {
    		return new ResponseEntity<>("Invalid phone number!", HttpStatus.BAD_REQUEST);
    	}
    	
    	User user = new User();
        user.setName(registerDto.getName());
        user.setPhonenumber(registerDto.getPhonenumber());
        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        user.setAddress(registerDto.getAddress());
        user.setCreatedDate(Date.valueOf(LocalDate.now()));
        for (String roleName : registerDto.getRoles()) {
            Role role = roleRepository.findRoleByRoleName(roleName);
            user.getRoles().add(role);
        }
        userService.save(user);
//        userService.copyUser(user.getUserId());
        return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
    }
}
