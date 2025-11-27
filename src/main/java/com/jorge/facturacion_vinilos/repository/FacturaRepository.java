package com.jorge.facturacion_vinilos.repository;

import com.jorge.facturacion_vinilos.model.Factura;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacturaRepository extends JpaRepository<Factura, Integer> {
}
