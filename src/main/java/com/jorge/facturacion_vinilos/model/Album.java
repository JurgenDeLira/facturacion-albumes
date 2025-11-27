package com.jorge.facturacion_vinilos.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "albumes")
@Getter
@Setter
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String titulo;
    private String artista;
    private Integer anioDeLanzamiento;
    private String genero;
    private Integer anioDeEdicion;
    private String discografica;
    private String formato;
    private String detalle;
    private BigDecimal precio;

    @Column(updatable = false)//para que no se actualice si hay actualización
    @CreationTimestamp
    private LocalDateTime fechaCreado;
    @UpdateTimestamp
    private LocalDateTime fechaActualizado;
}
