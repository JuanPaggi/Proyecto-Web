package com.proyecto.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 *  Entidad de la tabla Etiquetas.
 */

@Entity
@Table(name = "etiquetas")
@Data
@Getter
@Setter
public class EtiquetaModels {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_etiqueta")
    private int idEtiqueta;

    @Column(name = "etiqueta")
    private String etiqueta;

}
