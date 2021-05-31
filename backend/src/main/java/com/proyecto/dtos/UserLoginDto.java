package com.proyecto.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * DTO para loguear un usuario en la aplicacion.
 */

@Getter
@Setter
public class UserLoginDto {

    @JsonProperty("user")
    private String user;

    @JsonProperty("clave")
    private String clave;

}
