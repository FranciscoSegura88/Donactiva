package com.donactiva.proyecto.model;

import jakarta.validation.constraints.Email;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "usuarios")
@RequiredArgsConstructor
@Getter @Setter
public class Usuarios {

    public enum Rol{
        USUARIO,
        INSTITUCION,
        ADMIN
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUsuario;

    @NonNull
    private String nombre;

    @NonNull
    @Email(message = "El correo debe de ser un correo")
    private String correo;

    @NonNull
    private String contraseña;

    @Enumerated(EnumType.STRING)
    private Rol rol = Rol.USUARIO;

    @Column(name = "puntosGanados", nullable = false)
    private int puntosGanados = 0;

    @Column(name = "puntosUsados", nullable = false)
    private int puntosUsados = 0;

    @Column(name = "puntosDisponibles", nullable = false)
    private int puntosDisponibles = 0;

}