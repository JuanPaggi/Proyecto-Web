package com.proyecto.models;

import lombok.Getter;
import lombok.Setter;


import javax.persistence.*;
import java.util.Date;


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


}
