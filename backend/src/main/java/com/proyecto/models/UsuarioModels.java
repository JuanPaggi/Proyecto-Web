package com.proyecto.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Data
@Getter
@Setter
@Table(name = "usuarios")
public class UsuarioModels {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private int IdUsuario;

    @Column(name = "user")
    private String user;

    @Column(name = "clave")
    private String clave;

    @Column(name = "mail")
    private String mail;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido")
    private String apellido;

    @Column(name = "mail_verificado")
    private Boolean mailVerificado;

    @Column(name = "fecha_registro")
    private Date fechaRegistro;

    @Column(name = "fecha_nacimiento")
    private Date fechaNacimiento;

    @Column(name = "codigo_verificacion")
    private String codigoVerificacion;

    @Column(name = "admin")
    private Boolean admin;

}
