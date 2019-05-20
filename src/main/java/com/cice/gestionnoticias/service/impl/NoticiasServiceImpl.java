package com.cice.gestionnoticias.service.impl;

import com.cice.gestionnoticias.controller.dto.NoticiaDTO;
import com.cice.gestionnoticias.repository.NoticiasRepository;
import com.cice.gestionnoticias.repository.entity.NoticiaEntity;
import com.cice.gestionnoticias.service.NoticiasService;
import com.cice.gestionnoticias.service.converter.DTOEntityConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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

    @Override
    public NoticiaDTO actualizarNoticiaById(Long id, NoticiaDTO noticiaDTO) {
        NoticiaDTO responseDTO = null;
        Optional<NoticiaEntity> entityOptional = noticiasRepository.findById(id);
        if(entityOptional.isPresent()){
            if(noticiaDTO.getCuerpo() != null){
                entityOptional.get().setCuerpo(noticiaDTO.getCuerpo());
            }
            if(noticiaDTO.getTitulo() != null){
                entityOptional.get().setTitulo(noticiaDTO.getTitulo());
            }
            if(noticiaDTO.getAutor() != null){
                entityOptional.get().setAutor(noticiaDTO.getAutor());
            }
            if(noticiaDTO.getFecha() != null){
                entityOptional.get().setFecha(noticiaDTO.getFecha());
            }
        }
        NoticiaEntity entitySaved = noticiasRepository.save(entityOptional.get());
        responseDTO = mapper.mapEntityToDTO(entitySaved);
        return responseDTO;
    }

    @Override
    public NoticiaDTO actualizarNoticia(NoticiaDTO noticiaDTO) {
        NoticiaDTO noticia = null;
        if(validateMandatoryFields(noticiaDTO)) {
            NoticiaEntity entity = noticiasRepository.save(mapper.mapDTOToEntity(noticiaDTO));
            noticia = mapper.mapEntityToDTO(entity);
        }
        return noticia;
    }

    private boolean validateMandatoryFields(NoticiaDTO noticia){
        return !StringUtils.isEmpty(noticia.getTitulo()) &&
                !StringUtils.isEmpty(noticia.getCuerpo()) &&
                !StringUtils.isEmpty(noticia.getAutor()) &&
                !StringUtils.isEmpty(noticia.getFecha());
    }
}
