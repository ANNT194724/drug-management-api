package com.example.demo.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class RegisterDto {
    @NotBlank
    private String name;
    @NotBlank
    private String phonenumber;
    @Email
    private String email;
    @Size(min = 8)
    private String password;
    private String address;
    private List<String> roles;
}
