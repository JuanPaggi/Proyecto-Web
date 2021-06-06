package com.proyecto.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * DTO para crear un comentario.
 */

@Getter
@Setter
public class CommentCreateDto {

    @JsonProperty("texto")
    @NotNull
    private String texto;

    @JsonProperty("id_publicacion")
    @NotNull
    private int idPublicacion;
}
