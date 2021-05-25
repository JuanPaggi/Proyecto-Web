package com.proyecto.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Data
@Getter
@Setter
public class GetPublicacionDto {

    @JsonProperty("idPublicacion")
    private int idPublicacion;

    @JsonProperty("descripcion")
    private String descripcion;

    @JsonProperty("fechaCreacion")
    private Date fechaCreacion;

    @JsonProperty("titulo")
    private String titulo;

    @JsonProperty("etiquetas")
    private List<GetEtiquetaDto> etiquetas;

    @JsonProperty("comentarios")
    private List<GetComentarioDto> comentarios;

    @JsonProperty ("usuario")
    private GetShortUsuarioDto usuario;

}
