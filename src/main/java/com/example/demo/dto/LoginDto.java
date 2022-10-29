package com.example.demo.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginDto {
    private String email;
    private String phonenumber;
    @NotBlank
    private String password;
}
