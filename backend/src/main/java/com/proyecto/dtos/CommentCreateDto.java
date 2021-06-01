package com.proyecto.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * DTO para crear un comentario.
 */

@Getter
@Setter
public class CommentCreateDto {

    @JsonProperty("texto")
    private String texto;

    @JsonProperty("id_publicacion")
    private int idPublicacion;
}
