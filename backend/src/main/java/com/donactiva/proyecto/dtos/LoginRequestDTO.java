package com.donactiva.proyecto.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class LoginRequestDTO {
    private String correo;
    private String contraseña;
}