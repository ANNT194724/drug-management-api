package com.example.demo.model;

import java.sql.Date;
import java.util.Collection;
import java.util.Collections;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Setter
@Getter
@ToString
@Entity
@Table(name = "users")

public class User {
	@Id
	@GeneratedValue
	private Integer userId;
	private String name;
	private String password;
	private Date createdDate;
	@Column(unique = true)
	private String phonenumber;
	@Column(unique = true)
	private String email;
	private String address;
	
	public Collection<? extends GrantedAuthority> getRoles() {        
		return Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"));
    }
}
