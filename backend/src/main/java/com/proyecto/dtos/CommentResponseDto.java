package com.proyecto.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * DTO para devolver los datos de un comentario.
 */

@Getter
@Setter
public class CommentResponseDto {

    @JsonProperty("id_comentario")
    private int idComentario;

    @JsonProperty("texto")
    private String texto;

    @JsonProperty("fechaCreacion")
    private Date fechaCreacion;

}
