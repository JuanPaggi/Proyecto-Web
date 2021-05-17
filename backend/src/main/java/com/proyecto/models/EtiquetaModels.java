package com.proyecto.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 *  Entidad de la tabla Etiquetas.
 */

@Data
@Getter
@Setter
@Entity
@Table(name = "etiquetas")
public class EtiquetaModels {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_etiqueta")
    private int idEtiqueta;

    @Column(name = "etiqueta")
    private String etiqueta;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "publicaciones_etiquetas",
            joinColumns =
            @JoinColumn(name = "id_etiqueta", referencedColumnName = "id_etiqueta"),
            inverseJoinColumns =
            @JoinColumn(name = "id_publicacion", referencedColumnName = "id_publicacion")
    )
    private List<PublicacionModels> publicaciones = new ArrayList<>();

}
