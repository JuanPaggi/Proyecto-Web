package com.proyecto.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * DTO para crear una publicacion.
 */

@Getter
@Setter
public class PublicationCreateDto {

    @JsonProperty("descripcion")
    private String descripcion;

    @JsonProperty("titulo")
    private String titulo;

    @JsonProperty("etiquetas")
    private List<Integer> etiquetas;

}
