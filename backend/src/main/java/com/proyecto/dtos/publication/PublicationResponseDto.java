package com.proyecto.dtos.publication;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.proyecto.dtos.tag.TagResponseDto;
import com.proyecto.dtos.user.UserNameResponseDto;
import com.proyecto.dtos.comment.CommentResponseDto;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

/**
 * DTO para devolver los datos de una publicacion.
 */

@Getter
@Setter
public class PublicationResponseDto {

    @JsonProperty("id_publicacion")
    private int idPublicacion;

    @JsonProperty("descripcion")
    private String descripcion;

    @JsonProperty("fechaCreacion")
    private Date fechaCreacion;

    @JsonProperty("titulo")
    private String titulo;

    @JsonProperty("etiquetas")
    private List<TagResponseDto> etiquetas;

    @JsonProperty("comentarios")
    private List<CommentResponseDto> comentarios;

    @JsonProperty("usuario")
    private UserNameResponseDto usuario;

}
