package com.proyecto.models;

import lombok.Getter;
import lombok.Setter;


import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "galerias")
public class GaleriaModels {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_galeria")
    private int idGaleria;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "fecha_creacion")
    private Date fechaCreacion;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "imagenes_galerias",
            joinColumns =
            @JoinColumn(name = "id_galeria", referencedColumnName = "id_galeria"),
            inverseJoinColumns =
            @JoinColumn(name = "id_imagen", referencedColumnName = "id_imagen")
    )
    private List<ImagenModels> imagenes;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "galerias_etiquetas",
            joinColumns =
            @JoinColumn(name = "id_galeria", referencedColumnName = "id_galeria"),
            inverseJoinColumns =
            @JoinColumn(name = "id_etiqueta", referencedColumnName = "id_etiqueta")
    )
    private List<EtiquetaModels> etiquetas;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    private UsuarioModels usuario;
}
