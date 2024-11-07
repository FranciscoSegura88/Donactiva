package com.donactiva.proyecto.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class LoginResponseDTO {
    private boolean success;
    private String message;
    private String token;

    public LoginResponseDTO(boolean success, String message, String token) {
        this.success = success;
        this.message = message;
        this.token = token;
    }
}
