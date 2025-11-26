package com.jorge.facturacion_vinilos.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "albumes")
@Getter
@Setter
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    private String banda;
    private Integer anioDeLanzamiento;
    private String genero;
    private Integer anioDeEdicion;
    private String discografica;
    private String formato;
}
