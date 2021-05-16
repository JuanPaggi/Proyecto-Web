package com.proyecto.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

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

}
