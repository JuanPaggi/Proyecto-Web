package com.proyecto.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Modelo correspondiente a la tabla usuarios.
 */

@Getter
@Setter
@Entity
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_imagen", referencedColumnName = "id_imagen")
    private ImagenModels imagenPerfil;

    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario", nullable = false, insertable = false, updatable = false)
    private List<PublicacionModels> publicaciones;

    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario", nullable = false, insertable = false, updatable = false)
    private List<ComentarioModels> comentarios;

}
