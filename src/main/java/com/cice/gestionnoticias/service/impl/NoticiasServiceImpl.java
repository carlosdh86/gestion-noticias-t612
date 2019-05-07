package com.cice.gestionnoticias.service.impl;

import com.cice.gestionnoticias.controller.dto.NoticiaDTO;
import com.cice.gestionnoticias.repository.NoticiasRepository;
import com.cice.gestionnoticias.repository.entity.NoticiaEntity;
import com.cice.gestionnoticias.service.NoticiasService;
import com.cice.gestionnoticias.service.converter.NoticiaDTOtoNoticiaEntityConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoticiasServiceImpl implements NoticiasService {

    @Autowired
    NoticiasRepository noticiasRepository;

    NoticiaDTOtoNoticiaEntityConverter mapper = new NoticiaDTOtoNoticiaEntityConverter();

    @Override
    public NoticiaDTO crearNoticia(NoticiaDTO noticia) {
        NoticiaDTO responseDTO = null;
        NoticiaEntity noticiaEntity = noticiasRepository.save(mapper.mapTo(noticia));
        return responseDTO;
    }
}
