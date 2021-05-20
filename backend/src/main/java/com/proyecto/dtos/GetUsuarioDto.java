package com.proyecto.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

/*
    que datos quiero devolver / enviar
 */

@Data
@Getter
@Setter
public class GetUsuarioDto {

    @JsonProperty ("idUsuario")
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



}
