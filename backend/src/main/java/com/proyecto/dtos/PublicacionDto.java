package com.proyecto.dtos;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Data
@Getter
@Setter
public class PublicacionDto {

    @JsonProperty("descripcion")
    private String descripcion;

    @JsonProperty("titulo")
    private String titulo;

    @JsonProperty("etiquetas")
    private List<Integer> etiquetas;

    @JsonProperty("comentarios")
    private List<Integer> comentarios;

}
