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

    public void setImagen(byte[] imagen) throws NoSuchAlgorithmException {
        this.imagenHash = Sha1Hasher.hashBytes(imagen);
        this.imagen = imagen;
    }

}
