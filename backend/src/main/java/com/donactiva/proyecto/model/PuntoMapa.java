package com.donactiva.proyecto.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "puntoMapa")
@Getter @Setter
public class PuntoMapa {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPuntoMapa;

    private String nombre;
    private double lat;
    private double lng;

    // Constructor
    public PuntoMapa(String nombre, double lat, double lng) {
        this.nombre = nombre;
        this.lat = lat;
        this.lng = lng;
    }

}
