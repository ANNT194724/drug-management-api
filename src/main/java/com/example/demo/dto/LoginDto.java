package com.example.demo.dto;

import lombok.Data;

@Data
public class LoginDto {
//    private String phonenumberEmail;
    private String email;
    private String phonenumber;
    private String password;
}