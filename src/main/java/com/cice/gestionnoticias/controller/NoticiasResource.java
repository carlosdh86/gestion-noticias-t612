package com.cice.gestionnoticias.controller;

import com.cice.gestionnoticias.controller.dto.NoticiaDTO;
import com.cice.gestionnoticias.service.NoticiasService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *  Clase resource de Noticias.
 *  Vamos a definir todos los metodos de un CRUD
 */
@RestController
@Api(value = "Sistema de gestion de noticias", description = "Descripcion del servicio de gestion noticias")
public class NoticiasResource {

    @Autowired
    NoticiasService noticiasService;

    /**
     * Este metodo sirve para crear un recurso nuevo
     *
     * Dado un modelo noticia, persistiremos esta en la DB y devolveremos como respuesta el mismo objeto
     * creado junto a su identificador unico.
     *
     * @param noticia DTO con la información de la noticia para dar de alta en el sistema
     * @return ResponseEntity<NoticiaDTO>
     */
    @ApiOperation(value = "Metodo para crear noticias", httpMethod = "POST", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(
                    code = 201,
                    message = "Noticia creada",
                    response = ResponseEntity.class
            ),
            @ApiResponse(
                    code = 400,
                    message = "Bad request. Faltan campos obligatorios",
                    response = ResponseEntity.class
            ),
            @ApiResponse(code = 401, message = "operacion no autorizada", response = ResponseEntity.class)
    })
    @RequestMapping(path = "/noticias", method = RequestMethod.POST)
    public ResponseEntity<NoticiaDTO> crearNoticias(
            @ApiParam(value = "Objeto Noticia", required = true) @RequestBody NoticiaDTO noticia
    ){
        ResponseEntity<NoticiaDTO> response = null;
        if(validateNoticia(noticia)){
            // La noticia es valida
            NoticiaDTO noticiaDTO = noticiasService.crearNoticia(noticia);
            response = ResponseEntity.ok(noticiaDTO);
        } else {
            response = ResponseEntity.badRequest().body(noticia);
        }
        return response;
    }

    /**
     * Metodo para recuperar un recurso en base a el id solicitado
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "Recuperamos una noticia en base a un id dado", httpMethod = "GET", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 400, message = "Peticion no valida"),
            @ApiResponse(code = 404, message = "Noticia no encontrada")
    })
    @RequestMapping(path = "/noticias/{id}", method = RequestMethod.GET)
    public ResponseEntity<NoticiaDTO> getNoticiasById(@ApiParam(value = "Id de la noticia", required = true) @PathVariable(name = "id") Long id) {
        ResponseEntity<NoticiaDTO> response = null;

        NoticiaDTO noticiaDTO = noticiasService.buscarNoticiaById(id);
        if(noticiaDTO != null) {
            response = ResponseEntity.ok(noticiaDTO);
        } else {
            response = ResponseEntity.notFound().build();
        }

        return response;
    }

    /**
     *
     * @return
     */
    @ApiOperation(value = "Recuperamos todas las noticias almacenadas en DB", httpMethod = "GET", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 400, message = "Peticion no valida")
    })
    @RequestMapping(path = "/noticias", method = RequestMethod.GET)
    public ResponseEntity<List<NoticiaDTO>> getAllNoticias(){
        ResponseEntity<List<NoticiaDTO>> response = null;

        List<NoticiaDTO> allNoticias = noticiasService.getAllNoticias();
        response = ResponseEntity.ok(allNoticias);

        return response;
    }

    /**
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "Eliminar una noticia almacenada en DB dado un id", httpMethod = "DELETE", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 202, message = "Acceptado"),
            @ApiResponse(code = 400, message = "Peticion no valida"),
            @ApiResponse(code = 404, message = "Noticia no encontrada")
    })
    @RequestMapping(path = "/noticias/{id}", method = RequestMethod.DELETE)
    public ResponseEntity eliminarNoticiaById(@ApiParam(value = "Id de la noticia", required = true) @PathVariable(name = "id") Long id) {
        noticiasService.eliminarNoticiaById(id);
        return ResponseEntity.accepted().build();
    }

    /**
     * Metodo que actualizara datos de una noticia dado su id
     *
     * @param id
     * @param noticiaDTO
     * @return
     */
    @ApiOperation(value = "Actualizar una noticias almacenada en DB", httpMethod = "PATCH", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "ok"),
            @ApiResponse(code = 400, message = "Peticion no valida"),
            @ApiResponse(code = 404, message = "Noticia no encontrada")
    })
    @RequestMapping(path = "/noticias/{id}", method = RequestMethod.PATCH)
    public ResponseEntity<NoticiaDTO> actualizarNoticiaById(
            @ApiParam(value = "Identificador de la noticia", required = true) @PathVariable(name = "id") Long id,
            @ApiParam(value = "Datos para acutializar la noticia", required = true) @RequestBody NoticiaDTO noticiaDTO) {
        ResponseEntity<NoticiaDTO> responseDTO = null;

        NoticiaDTO noticiaActualizada = noticiasService.actualizarNoticiaById(id, noticiaDTO);
        responseDTO = ResponseEntity.ok(noticiaActualizada);

        return responseDTO;
    }

    /**
     * Metodo que actualiza de forma idempotente una noticia en DB
     *
     * @param noticia
     * @return
     */
    @ApiOperation(value = "Actualizar/Crear una noticia almacenada en DB", httpMethod = "PUT", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "ok"),
            @ApiResponse(code = 202, message = "Acceptado"),
            @ApiResponse(code = 400, message = "Peticion no valida")
    })
    @RequestMapping(path = "/noticias", method = RequestMethod.PUT)
    public ResponseEntity<NoticiaDTO> actualizarNoticia(@ApiParam(value = "Cuerpo de la noticia a actualizar", required = true) @RequestBody NoticiaDTO noticia) {
        ResponseEntity<NoticiaDTO> responseDTO = null;
        NoticiaDTO noticiaDTO = noticiasService.actualizarNoticia(noticia);
        if(noticiaDTO != null){
            responseDTO = ResponseEntity.ok(noticiaDTO);
        } else {
            responseDTO = ResponseEntity.badRequest().build();
        }
        return responseDTO;
    }


    private boolean validateNoticia(NoticiaDTO noticia){
        return (noticia.getTitulo().isEmpty() || noticia.getCuerpo().isEmpty()) ? false : true;
    }

}
