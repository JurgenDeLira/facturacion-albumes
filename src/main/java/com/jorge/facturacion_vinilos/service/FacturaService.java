package com.jorge.facturacion_vinilos.service;

import com.jorge.facturacion_vinilos.dto.RequestDetalleFacturaDTO;
import com.jorge.facturacion_vinilos.dto.RequestFacturaDTO;
import com.jorge.facturacion_vinilos.dto.ResponseFacturaDTO;
import com.jorge.facturacion_vinilos.model.Album;
import com.jorge.facturacion_vinilos.model.DetalleFactura;
import com.jorge.facturacion_vinilos.model.Factura;
import com.jorge.facturacion_vinilos.repository.AlbumRepository;
import com.jorge.facturacion_vinilos.repository.FacturaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class FacturaService {

    private final FacturaRepository facturaRepository;
    private final AlbumRepository albumRepository;
    private final ModelMapper modelMapper;

    public FacturaService(FacturaRepository facturaRepository,
                          AlbumRepository albumRepository,
                          ModelMapper modelMapper) {
        this.facturaRepository = facturaRepository;
        this.albumRepository = albumRepository;
        this.modelMapper = modelMapper;
    }

    //findAll
    public List<ResponseFacturaDTO> findAll() {
        return facturaRepository.findAll().stream().map(
                factura -> modelMapper.map(factura, ResponseFacturaDTO.class)
        ).collect(Collectors.toList());
    }

    //findById
    public Optional<ResponseFacturaDTO> findById(Integer id) {
        return facturaRepository.findById(id).map(
                factura -> modelMapper.map(factura, ResponseFacturaDTO.class)
        );
    }

    //deleteById
    public void deleteById(Integer id) {
        facturaRepository.findById(id);
    }

    //save
    @Transactional
    public ResponseFacturaDTO save(RequestFacturaDTO requestFacturaDTO) {
        Factura factura = new Factura();
        BigDecimal subtotalFactura = BigDecimal.ZERO;
        Set<DetalleFactura> detalles = new HashSet<>();

        factura.setNumeroFactura(requestFacturaDTO.getNumeroFactura());

        for (RequestDetalleFacturaDTO detalleFacturaDTO : requestFacturaDTO.getDetalleFacturas()) {
            Album album = albumRepository.findById(detalleFacturaDTO.getIdAlbum()).orElseThrow(
                    () -> new RuntimeException("Producto no encontrado")
            );

            BigDecimal totalAlbum = album.getPrecio().multiply(
                    BigDecimal.valueOf(detalleFacturaDTO.getCantidad()));

            subtotalFactura = subtotalFactura.add(totalAlbum);

            DetalleFactura detalleFactura = new DetalleFactura();
            detalleFactura.setIdAlbum(detalleFacturaDTO.getIdAlbum());
            detalleFactura.setPrecio(album.getPrecio());
            detalleFactura.setCantidad(detalleFacturaDTO.getCantidad());
            detalleFactura.setTotal(totalAlbum);
            detalleFactura.setFactura(factura);

            detalles.add(detalleFactura);
        }

        factura.setDetalleFacturas(detalles);
        factura.setSubTotal(subtotalFactura);
        factura.setTotal(subtotalFactura.add(subtotalFactura
                .multiply(BigDecimal.valueOf(factura.getIVA()))));

        Factura savedFactura = facturaRepository.save(factura);

        return modelMapper.map(savedFactura, ResponseFacturaDTO.class);
    }

}