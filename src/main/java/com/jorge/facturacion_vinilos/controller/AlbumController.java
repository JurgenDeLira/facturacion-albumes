package com.jorge.facturacion_vinilos.controller;

import com.jorge.facturacion_vinilos.dto.AlbumDTO;
import com.jorge.facturacion_vinilos.service.AlbumService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v2/albumes")
public class AlbumController {
    private final AlbumService albumService;

    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @PostMapping
    public ResponseEntity<AlbumDTO> save (@RequestBody AlbumDTO albumDto){
        return new ResponseEntity<>(albumService.save(albumDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<AlbumDTO>> findAll(){
        List<AlbumDTO> albumDTOS = albumService.findAll();
        if (albumDTOS.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(albumDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlbumDTO> findById(@PathVariable Integer id){
        return albumService.findById(id).map(
                albumDTO -> {
                    return ResponseEntity.ok(albumDTO);
                }
        ).orElseGet(()->ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id){
        if (albumService.deleteById(id))
            return ResponseEntity.noContent().build();
        return ResponseEntity.notFound().build();
    }

    @PutMapping
    public ResponseEntity<AlbumDTO> update(@RequestBody AlbumDTO albumDTO){
        return albumService.update(albumDTO).map(
                ResponseEntity::ok
        ).orElseGet(
                () -> ResponseEntity.notFound().build()
        );
    }
}
