package com.cice.gestionnoticias.service;

import com.cice.gestionnoticias.controller.dto.NoticiaDTO;

import java.util.List;

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

    /**
     * Metodo que devolverá un objeto noticiaDTO siempre que se encuentre el id en la DB
     *
     * @param id de la noticia
     * @return NoticiaDTO
     */
    NoticiaDTO buscarNoticiaById(Long id);

    /**
     * Metodo que devuelve una lista con todos las noticias disponibles en DB
     *
     * @return List<NoticiaDTO>
     */
    List<NoticiaDTO> getAllNoticias();

    /**
     * Metodo que elimina una noticia del recurso en base a un id
     *
     * @param id
     */
    void eliminarNoticiaById(Long id);

    /**
     * Metodo para actualizar una noticia en base a los datos recibidos y un id de identificación
     * @param id
     * @param noticiaDTO
     * @return
     */
    NoticiaDTO actualizarNoticiaById(Long id, NoticiaDTO noticiaDTO);

    /**
     * Metodo que actualizara una noticia de manera idempotente en DB
     * @param noticiaDTO
     * @return
     */
    NoticiaDTO actualizarNoticia(NoticiaDTO noticiaDTO);
}
