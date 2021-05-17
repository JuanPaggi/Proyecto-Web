package com.proyecto.models;

import lombok.Getter;
import lombok.Setter;


import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "publicaciones")
public class PublicacionModels {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_publicacion")
    private int idPublicacion;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "fecha_creacion")
    private Date fechaCreacion;

    @Column(name = "titulo")
    private String titulo;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "publicaciones_etiquetas",
            joinColumns =
            @JoinColumn(name = "id_publicacion", referencedColumnName = "id_publicacion"),
            inverseJoinColumns =
            @JoinColumn(name = "id_etiqueta", referencedColumnName = "id_etiqueta")
    )
    private List<EtiquetaModels> etiquetas;

}
