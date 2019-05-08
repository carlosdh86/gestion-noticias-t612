package com.cice.gestionnoticias.service.converter;

import com.cice.gestionnoticias.controller.dto.NoticiaDTO;
import com.cice.gestionnoticias.repository.entity.NoticiaEntity;

public class DTOEntityConverter {

    public NoticiaEntity mapDTOToEntity(NoticiaDTO noticiaDTO){
        NoticiaEntity noticiaEntity = null;

        noticiaEntity = new NoticiaEntity()
                .setId(noticiaDTO.getId())
                .setTitulo(noticiaDTO.getTitulo())
                .setCuerpo(noticiaDTO.getCuerpo())
                .setAutor(noticiaDTO.getAutor())
                .setFecha(noticiaDTO.getFecha());

        return noticiaEntity;
    }

    public NoticiaDTO mapEntityToDTO(NoticiaEntity noticiaEntity){
        NoticiaDTO noticiaDTO = null;

        noticiaDTO = new NoticiaDTO()
                .setId(noticiaEntity.getId())
                .setTitulo(noticiaEntity.getTitulo())
                .setCuerpo(noticiaEntity.getCuerpo())
                .setAutor(noticiaEntity.getAutor())
                .setFecha(noticiaEntity.getFecha());

        return  noticiaDTO;
    }
}
