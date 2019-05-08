package com.cice.gestionnoticias.service.impl;

import com.cice.gestionnoticias.controller.dto.NoticiaDTO;
import com.cice.gestionnoticias.repository.NoticiasRepository;
import com.cice.gestionnoticias.repository.entity.NoticiaEntity;
import com.cice.gestionnoticias.service.NoticiasService;
import com.cice.gestionnoticias.service.converter.DTOEntityConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NoticiasServiceImpl implements NoticiasService {

    @Autowired
    NoticiasRepository noticiasRepository;

    DTOEntityConverter mapper = new DTOEntityConverter();

    @Override
    public NoticiaDTO crearNoticia(NoticiaDTO noticia) {
        NoticiaDTO responseDTO = null;
        NoticiaEntity noticiaEntity = noticiasRepository.save(mapper.mapDTOToEntity(noticia));
        responseDTO = mapper.mapEntityToDTO(noticiaEntity);
        return responseDTO;
    }

    @Override
    public NoticiaDTO buscarNoticiaById(Long id) {
        NoticiaDTO responseDTO = null;

        Optional<NoticiaEntity> optionalNoticiaEntity = noticiasRepository.findById(id);
        if(optionalNoticiaEntity.isPresent()){
            responseDTO = mapper.mapEntityToDTO(optionalNoticiaEntity.get());
        }

        return responseDTO;
    }

    @Override
    public List<NoticiaDTO> getAllNoticias() {
        List<NoticiaDTO> listaNoticias = Collections.EMPTY_LIST;

        listaNoticias = noticiasRepository
                .findAll()
                .stream()
                .map(entity -> mapper.mapEntityToDTO(entity))
                .collect(Collectors.toList());

        return listaNoticias;
    }

    @Override
    public void eliminarNoticiaById(Long id) {
        noticiasRepository.deleteById(id);
    }
}
