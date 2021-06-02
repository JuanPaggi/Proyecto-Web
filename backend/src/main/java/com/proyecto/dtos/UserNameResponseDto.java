package com.proyecto.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * DTO para devolver el user name del usuario.
 */

@Getter
@Setter
public class UserNameResponseDto {

    @JsonProperty("id_usuario")
    private Integer idUsuario;

    @JsonProperty("user")
    private String user;
}
