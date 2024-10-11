package com.donactiva.proyecto.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "donacion")
@NoArgsConstructor
@Getter @Setter
public class Donacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idDonacion;

    @ManyToOne
    @JoinColumn(name = "idArticulo", nullable = false)
    private Articulos articulo;

    @ManyToOne
    @JoinColumn(name = "idLocalizacoin", nullable = false)
    private Localizacion localizacion;

    @ManyToOne
    @JoinColumn(name = "idUsuario", nullable = false)
    private Usuarios usuario;

}
