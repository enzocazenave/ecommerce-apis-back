package com.apis.ecommerce.entities.dto;

import lombok.Data;

@Data
public class UserResponse {    
    public UserResponse(Long id, String name, String surname, String email, boolean admin) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.admin = admin;
    }

    private Long id;
    private String name;
    private String surname;
    private String email;
    private boolean admin;
}
