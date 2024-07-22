package com.apis.ecommerce.entities.dto;

import lombok.Data;

@Data
public class UserRequest {
    private String name;
    private String surname;
    private String email;
    private String password;
}