package com.proyecto.dtos.gallery;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GalleryCreateDto {

    @JsonProperty("titulo")
    private String titulo;

    @JsonProperty("descripcion")
    private String descripcion;

    @JsonProperty("etiquetas")
    private List<Integer> etiquetas;

    @JsonProperty("imagenes")
    private List<byte[]> imagenes;

}
