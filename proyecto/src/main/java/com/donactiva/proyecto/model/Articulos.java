package com.donactiva.proyecto.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Getter;

@Entity
@Table(name = "articulos")
@NoArgsConstructor
@Getter @Setter
public class Articulos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idArticulo;

    private int noPerecederos;
    private int higiene;
    private int textiles;
    private int juguetes;

    Articulos(int noPerecederos, int higiene, int textiles, int juguetes){
        
        this.noPerecederos = noPerecederos;
        this.higiene = higiene;
        this.textiles = textiles;
        this.juguetes = juguetes;

    }

}
