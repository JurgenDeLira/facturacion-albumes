package com.jorge.facturacion_vinilos.dto;

import lombok.Data;

@Data
public class RequestDetalleFacturaDTO {
    private Integer idProducto;
    private Integer cantidad;
}
