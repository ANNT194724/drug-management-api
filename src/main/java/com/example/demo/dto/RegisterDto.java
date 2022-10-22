package com.example.demo.dto;

import lombok.Data;

@Data
public class RegisterDto {
    private String name;
    private String phonenumber;
    private String email;
    private String password;
    private String address;
}
