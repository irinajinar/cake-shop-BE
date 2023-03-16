package com.project.CakeShop.model.dto;

import lombok.Data;

@Data
public class UserRegisterDto {
    private String email;
    private String firstName;
    private String lastName;
    private String address;
    private String telephoneNumber;
    private String password;
}
