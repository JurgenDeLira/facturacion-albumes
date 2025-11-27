package com.jorge.facturacion_vinilos.repository;

import com.jorge.facturacion_vinilos.model.Album;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlbumRepository extends JpaRepository<Album, Integer> {
}
