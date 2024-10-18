package com.donactiva.proyecto.model;

import org.hibernate.annotations.CreationTimestamp;

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

@Entity
@Table(name = "donacion")
@NoArgsConstructor
@Getter @Setter
public class Donacion {

    private LocalDateTime fechaRecolectada;

    public enum EstadoDonacion {
        PENDIENTE,
        CADUCADA,
        RECOLECTADA
    }

    public void setFechaRecolectada(LocalDateTime fechaRecolectada){
        this.fechaRecolectada = fechaRecolectada;
        this.estado = EstadoDonacion.RECOLECTADA;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idDonacion;

    @ManyToOne
    @JoinColumn(name = "idArticulo", nullable = false)
    private Articulos articulo;

    @ManyToOne
    @JoinColumn(name = "idLocalizacion", nullable = false)
    private Localizacion localizacion;

    @ManyToOne
    @JoinColumn(name = "idUsuario", nullable = false)
    private Usuarios usuario;

    @Column(name = "fecha_Creacion", nullable = true, updatable = false)
    @CreationTimestamp
    private LocalDateTime fechaCreacion;

    @Enumerated(EnumType.STRING)
    private EstadoDonacion estado = EstadoDonacion.PENDIENTE;
    
}
