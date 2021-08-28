package com.proyecto.dtos.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * DTO para recibir los datos de una verificacion de mail.
 */

@Getter
@Setter
public class VerificacionCodigoDto {

    @JsonProperty("user")
    @NotNull
    private String user;

    @JsonProperty ("clave")
    @NotNull
    private String clave;

    @JsonProperty ("codigo")
    @NotNull
    private String codigo;

}
