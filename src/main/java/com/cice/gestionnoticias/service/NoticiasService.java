package com.cice.gestionnoticias.service;

import com.cice.gestionnoticias.controller.dto.NoticiaDTO;

/**
 * Interfaz que contendra los metodos de la logica de negocio para el resource Noticias
 */
public interface NoticiasService {

    /**
     * Metodo que crea una nueva noticia en base a los datos recibidos desde el resource
     *
     * @param noticia para almacenar en base de datos
     * @return NoticiaDTO con el ID unico
     */
    NoticiaDTO crearNoticia(NoticiaDTO noticia);
}
