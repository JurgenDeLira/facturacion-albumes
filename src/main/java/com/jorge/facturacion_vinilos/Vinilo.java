package com.jorge.facturacion_vinilos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Vinilo {
    private Integer id;
    private String nombre;
    private String banda;
    private Integer anio;
    private String genero;

}
