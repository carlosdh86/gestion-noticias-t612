package com.cice.gestionnoticias.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NoticiaDTO {

    private Long id;
    private String titulo;
    private String cuerpo;
    private String autor;
    private String fecha;

}
