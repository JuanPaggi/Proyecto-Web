package com.proyecto.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * DTO para crear un usuario.
 */

@Getter
@Setter
public class UserCreateDto {

    @JsonProperty("user")
    @NotNull
    private String user;

    @JsonProperty("clave")
    @NotNull
    private String clave;

    @JsonProperty("mail")
    @NotNull
    private String mail;

    @JsonProperty("nombre")
    @NotNull
    private String nombre;

    @JsonProperty("apellido")
    @NotNull
    private String apellido;

    @JsonProperty("fechaNacimiento")
    @NotNull
    private Date fechaNacimiento;

}
