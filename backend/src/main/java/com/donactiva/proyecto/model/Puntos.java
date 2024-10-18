package com.donactiva.proyecto.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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

    private LocalDateTime fechaDeUso;

    public enum EstadoPuntos {
        DISPONIBLE,
        USADO
    }

    public void setFechaDeUso(LocalDateTime fechaDeUso){
        this.fechaDeUso = fechaDeUso;
        this.estado = EstadoPuntos.USADO;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPuntos;

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

    @Enumerated(EnumType.STRING)
    private EstadoPuntos estado = EstadoPuntos.DISPONIBLE;
}
