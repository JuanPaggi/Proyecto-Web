package com.proyecto.dtos.publication;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * DTO para crear una publicacion.
 */

@Getter
@Setter
public class PublicationCreateDto {

    @JsonProperty("titulo")
    @NotNull
    private String titulo;

    @JsonProperty("descripcion")
    @NotNull
    private String descripcion;

    @JsonProperty("etiquetas")
    private List<Integer> etiquetas;

}
