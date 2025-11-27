package com.jorge.facturacion_vinilos.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data //Para crear métodos getter y setter y sobreescribir toString
public class AlbumDTO {
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
}
