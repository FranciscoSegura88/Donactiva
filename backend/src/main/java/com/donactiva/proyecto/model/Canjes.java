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
@Table(name = "canjes")
@NoArgsConstructor
@Getter @Setter
public class Canjes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCanje;

    @ManyToOne
    @JoinColumn(name = "idUsuario", nullable = false)
    private Usuarios usuario;

    @Column(name = "puntosCanjeados", nullable = false, updatable = false)
    private int puntosCanjeados;

    @Column(name = "fechaCanje", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime fechaCanje;

    @Column(name = "convenio", nullable = false, updatable = false)
    private String convenio;
}
