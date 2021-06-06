package com.proyecto.models;

import com.proyecto.utils.Sha1Hasher;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;

/**
 * Modelo correspondiente a la tabla imagenes.
 */

@Getter
@Setter
@Entity
@Table(name = "imagenes")
public class ImagenModels {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_imagen")
    private int idImagen;

    @Column(name = "imagen")
    private byte[] imagen;

    @Column(name = "imagen_hash")
    private byte[] imagenHash;

    @Column(name = "fecha_subida")
    private Date fechaSubida;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_imagen", referencedColumnName = "id_imagen", nullable = false, insertable = false, updatable = false)
    private List<UsuarioModels> usuarios;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_usuario", referencedColumnName = "id_usuario")
    private UsuarioModels usuario;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "imagenes_galerias",
            joinColumns =
            @JoinColumn(name = "id_imagen", referencedColumnName = "id_imagen"),
            inverseJoinColumns =
            @JoinColumn(name = "id_galeria", referencedColumnName = "id_galeria")
    )
    private List<GaleriaModels> galerias;

    public void setImagen(byte[] imagen) throws NoSuchAlgorithmException {
        this.imagenHash = Sha1Hasher.hashBytes(imagen);
        this.imagen = imagen;
    }

}
