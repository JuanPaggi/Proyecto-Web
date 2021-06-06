package com.proyecto.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class GalleryResponseDto {

    @JsonProperty("idGaleria")
    private int idGaleria;

    @JsonProperty("titulo")
    private String titulo;

    @JsonProperty("descripcion")
    private String descripcion;

    @JsonProperty("fechaCreacion")
    private Date fechaCreacion;

    @JsonProperty("etiquetas")
    private List<TagResponseDto> etiquetas;

    @JsonProperty("usuario")
    private UserNameResponseDto usuario;

    @JsonProperty("imagen")
    private List<ImageResponseDto> imagenes;

}
