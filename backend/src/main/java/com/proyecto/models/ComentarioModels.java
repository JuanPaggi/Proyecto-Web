package com.proyecto.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * Modelo correspondiente a la tabla comentarios.
 */

@Getter
@Setter
@Entity
@Table(name = "comentarios")
public class ComentarioModels {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_comentario")
    private int IdComentario;

    @Column(name = "texto")
    private String texto;

    @Column(name = "fecha_creacion")
    private Date fechaCreacion;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_publicacion", referencedColumnName = "id_publicacion")
    private PublicacionModels publicacion;

}
