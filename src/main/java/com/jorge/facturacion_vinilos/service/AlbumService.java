package com.jorge.facturacion_vinilos.service;


import com.jorge.facturacion_vinilos.dto.AlbumDto;
import com.jorge.facturacion_vinilos.model.Album;
import com.jorge.facturacion_vinilos.repository.AlbumRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlbumService {

    private final AlbumRepository albumRepository;
    private final ModelMapper modelMapper;

    //Intecto dependencias de arriba en un constructoe:

    public AlbumService(AlbumRepository albumRepository, ModelMapper modelMapper) {
        this.albumRepository = albumRepository;
        this.modelMapper = modelMapper;
    }

    //save
    public AlbumDto save (AlbumDto albumDto){
        Album album = modelMapper.map(albumDto, Album.class); //transformo DTO a Album
        return modelMapper.map(albumRepository.save(album), AlbumDto.class);
    }

    //findAll
    public List<AlbumDto> findAll(){
        return albumRepository.findAll() //obtengo lista de entidades
                .stream() //transformo a stream(se procesa elemento por elemnto
                .map(album -> modelMapper.map(album, AlbumDto.class)) //transforma cada album a albumdto
                .toList(); //volver a una lista
    }

    //findById
    public Optional<AlbumDto> findById(Integer id){
        return albumRepository.findById(id)
                .map(album -> modelMapper.map(album, AlbumDto.class));
    }

    //deleteById
    public boolean deleteById (Integer id){
        return albumRepository.findById(id).map(
                album -> {
                    albumRepository.delete(album);
                    return true;
                }
        ).orElse(false);
    }

    //update
    public Optional<AlbumDto> update(AlbumDto albumDto){
        Album album = modelMapper.map(albumDto, Album.class);
        return albumRepository.findById(album.getId()).map(
                albumDB -> {
                    return modelMapper.map(albumRepository.save(album), AlbumDto.class);
                }
        );
    }

}
