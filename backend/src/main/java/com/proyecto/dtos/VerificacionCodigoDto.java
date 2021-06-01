package com.proyecto.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * DTO para recibir los datos de una verificacion de mail.
 */

@Getter
@Setter
public class VerificacionCodigoDto {

    @JsonProperty("user")
    private String user;

    @JsonProperty ("clave")
    private String clave;

    @JsonProperty ("codigo")
    private String codigo;

}
