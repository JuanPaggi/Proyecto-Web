package com.proyecto.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/*
    que datos quiero recibir
 */

@Data
@Getter
@Setter
public class UsuarioDto {

    @JsonProperty ("user")
    private String user;

    @JsonProperty ("clave")
    private String clave;

    @JsonProperty ("mail")
    private String mail;

    @JsonProperty ("nombre")
    private String nombre;

    @JsonProperty ("apellido")
    private String apellido;

    @JsonProperty ("mailVerificado")
    private Boolean mailVerificado;

    @JsonProperty ("fechaNacimiento")
    private Date fechaNacimiento;

    @JsonProperty ("codigoVerificacion")
    private Boolean codigoVerificacion;

    @JsonProperty ("admin")
    private Boolean admin;

}
