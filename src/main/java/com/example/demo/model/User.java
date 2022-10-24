package com.example.demo.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

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
	
}
