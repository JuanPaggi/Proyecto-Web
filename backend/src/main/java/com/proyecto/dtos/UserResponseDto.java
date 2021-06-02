package com.proyecto.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

/**
 * DTO para devolver los datos de un usuario.
 */

@Getter
@Setter
public class UserResponseDto {

    @JsonProperty ("id_usuario")
    private int idUsuario;

    @JsonProperty ("user")
    private String user;

    @JsonProperty("mail")
    private String mail;

    @JsonProperty ("nombre")
    private String nombre;

    @JsonProperty ("apellido")
    private String apellido;

    @JsonProperty ("mailVerificado")
    private Boolean mailVerificado;

    @JsonProperty ("fechaRegistro")
    private Date fechaRegistro;

    @JsonProperty ("fechaNacimiento")
    private Date fechaNacimiento;

    @JsonProperty ("codigoVerificacion")
    private String codigoVerificacion;

    @JsonProperty ("admin")
    private Boolean admin;

    @JsonProperty ("idImagen")
    private int idImagen;

}
