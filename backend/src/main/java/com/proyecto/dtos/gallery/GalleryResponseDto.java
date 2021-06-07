package com.proyecto.dtos.gallery;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.proyecto.dtos.ImageResponseDto;
import com.proyecto.dtos.tag.TagResponseDto;
import com.proyecto.dtos.user.UserNameResponseDto;
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

    @JsonProperty("imagenes")
    private List<ImageResponseDto> imagenes;

}
