package com.proyecto.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * DTO para modificar los datos de un usuario.
 * Se recibe tambien la clave actual para verificar la identidad.
 */

@Getter
@Setter
public class UserModifyDto {

    @JsonProperty("user")
    private String user;

    @JsonProperty("mail")
    private String mail;

    @JsonProperty("clave_actual")
    @NotNull
    private String clave;

    @JsonProperty("clave_nueva")
    private String nuevaClave;

}
