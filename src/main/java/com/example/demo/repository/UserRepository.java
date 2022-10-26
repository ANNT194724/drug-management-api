package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	User findByPhonenumberOrEmail(String phonenumber, String email);
    User findByUserId(Integer userId);
    Boolean existsByPhonenumber(String phonenumber);
    Boolean existsByEmail(String email);
}
