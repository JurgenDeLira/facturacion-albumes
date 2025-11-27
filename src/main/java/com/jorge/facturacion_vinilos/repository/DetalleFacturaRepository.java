package com.jorge.facturacion_vinilos.repository;

import com.jorge.facturacion_vinilos.model.DetalleFactura;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetalleFacturaRepository extends JpaRepository<DetalleFactura, Integer> {
}
