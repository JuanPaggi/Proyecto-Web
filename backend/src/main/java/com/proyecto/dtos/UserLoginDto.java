package com.proyecto.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * DTO para loguear un usuario en la aplicacion.
 */

@Getter
@Setter
public class UserLoginDto {

    @JsonProperty("user")
    @NotNull
    private String user;

    @JsonProperty("clave")
    @NotNull
    private String clave;

}
