package com.cice.gestionnoticias.service.impl;

import com.cice.gestionnoticias.controller.dto.NoticiaDTO;
import com.cice.gestionnoticias.repository.NoticiasRepository;
import com.cice.gestionnoticias.repository.entity.NoticiaEntity;
import com.cice.gestionnoticias.service.NoticiasService;
import com.cice.gestionnoticias.service.converter.DTOEntityConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
