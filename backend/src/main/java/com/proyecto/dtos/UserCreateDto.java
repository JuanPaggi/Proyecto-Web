package com.proyecto.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * DTO para crear un usuario.
 */

@Getter
@Setter
public class UserCreateDto {

    @JsonProperty("user")
    private String user;

    @JsonProperty("clave")
    private String clave;

    @JsonProperty("mail")
    private String mail;

    @JsonProperty("nombre")
    private String nombre;

    @JsonProperty("apellido")
    private String apellido;

    @JsonProperty("mailVerificado")
    private Boolean mailVerificado;

    @JsonProperty("fechaNacimiento")
    private Date fechaNacimiento;

    @JsonProperty("codigoVerificacion")
    private Boolean codigoVerificacion;

    @JsonProperty("admin")
    private Boolean admin;

    @JsonProperty("imagen")
    private byte[] imagen;

}
