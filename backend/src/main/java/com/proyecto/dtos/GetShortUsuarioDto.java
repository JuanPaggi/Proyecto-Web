package com.proyecto.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetShortUsuarioDto {

    @JsonProperty("idUsuario")
    private Integer idUsuario;

    @JsonProperty("user")
    private String user;
}
