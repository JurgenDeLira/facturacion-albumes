package com.jorge.facturacion_vinilos.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ResponseDetalleFacturaDTO {
    private Integer idProducto;
    private Integer cantidad;
    private BigDecimal precio;
    private BigDecimal total;
}
