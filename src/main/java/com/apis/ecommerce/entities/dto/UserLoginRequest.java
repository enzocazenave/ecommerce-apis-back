package com.apis.ecommerce.entities.dto;

import lombok.Data;

@Data
public class UserLoginRequest {
    private String email;
    private String password;
}
