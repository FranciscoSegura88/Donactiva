package com.donactiva.proyecto.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class LoginResponseDTO {
    private String message;
    private String token;

    public LoginResponseDTO(String message, String token) {
        this.message = message;
        this.token = token;
    }
}
