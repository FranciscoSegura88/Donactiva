package com.donactiva.proyecto.model;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
// import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "localizacion")
@RequiredArgsConstructor
@Getter @Setter
public class Localizacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idLocalizacion;

    @NonNull
    private String nombre;

    @NonNull
    private String domicilio;

    @NonNull
    private String telefono;

    @NonNull
    private double longitud;

    @NonNull
    private double latitud;
}
