package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.model.CustomUserDetails;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

@Service
@Transactional(readOnly = true)
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;

	private final RestTemplate restTemplate;

	public CustomUserDetailsService(UserRepository userRepository, RestTemplate restTemplate) {
        this.userRepository = userRepository;
		this.restTemplate = restTemplate;
	}
	
	@Override
	public CustomUserDetails loadUserByUsername(String phonenumberOrEmail) throws UsernameNotFoundException {
		User user = userRepository.findByPhonenumberOrEmail(phonenumberOrEmail, phonenumberOrEmail);

		if (user == null) {
            throw new UsernameNotFoundException(phonenumberOrEmail);
        }
        return new CustomUserDetails(user);
	}
	
	public UserDetails loadUserById(Integer userId) {
		User user = userRepository.findByUserId(userId);
		return new CustomUserDetails(user);
	}
    
    public Boolean existsByPhonenumber(String phonenumber) {
    	return userRepository.existsByPhonenumber(phonenumber);
    }
    
    public Boolean existsByEmail(String email) {
    	return userRepository.existsByEmail(email);
    }

	@Transactional
	public void save(User user) {
		userRepository.save(user);
	}

//	public ResponseEntity<?> copyUser(Integer id) {
//		return restTemplate.postForEntity(
//				"http://HELLO/hello",
//				userRepository.findByUserId(id), String.class);
//	}
	
}
