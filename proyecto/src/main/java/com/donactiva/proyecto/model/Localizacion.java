package com.donactiva.proyecto.model;

import java.util.List;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "localizacion")
@NoArgsConstructor
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

    Localizacion(String nombre, String domicilio, String telefono){

        this.nombre = nombre;
        this.domicilio = domicilio;
        this.telefono = telefono;

    }
}
