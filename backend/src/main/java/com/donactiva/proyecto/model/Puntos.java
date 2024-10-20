package com.donactiva.proyecto.model;

import jakarta.persistence.Column;
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
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;


@Entity
@Table(name = "puntos")
@NoArgsConstructor
@Getter @Setter
public class Puntos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPuntos;

    @ManyToOne
    @JoinColumn(name = "idUsuario", nullable = false)
    private Usuarios usuario;
    
    @ManyToOne
    @JoinColumn(name = "idDonacion", nullable = false)
    private Donacion donacion;

    @ManyToOne
    @JoinColumn(name = "idArticulo", nullable = false)
    private Articulos articulo;

    @Column(name = "fechaObtencion", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime fechaObtencion;

    @Column(name = "cantidad", nullable = false, updatable = false)
    private int cantidad;


}
