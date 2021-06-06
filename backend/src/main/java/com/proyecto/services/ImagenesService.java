package com.proyecto.services;

import com.proyecto.models.ImagenModels;
import com.proyecto.models.UsuarioModels;
import com.proyecto.repository.ImagenesRepository;
import com.proyecto.exceptions.ApiException;
import com.proyecto.utils.Constantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Optional;

/**
 * Capa de servicio para las imagenes.
 */

@Service
public class ImagenesService {

    @Autowired
    ImagenesRepository imagenesRepository;

    public byte[] obtenerBytePorId(int idImagen) {
        Optional<ImagenModels> imagen = imagenesRepository.findById(idImagen);
        if (imagen.isPresent()) {
            return imagen.get().getImagen();
        } else {
            throw new ApiException(404, Constantes.ERROR_NO_EXISTE);
        }
    }

    public ImagenModels obtenerImagenPorId(int idImagen) {
        Optional<ImagenModels> imagen = imagenesRepository.findById(idImagen);
        if (imagen.isPresent()) {
            return imagen.get();
        } else {
            throw new ApiException(404, Constantes.ERROR_NO_EXISTE);
        }
    }

    public Optional<ImagenModels> obtenerImagenPorHash(byte[] hash) {
        Optional<ImagenModels> imagenDB = imagenesRepository.findByHash(hash);
        return imagenDB;
    }

    public ImagenModels cargarImagen(byte[] imagen, UsuarioModels user) throws NoSuchAlgorithmException {
        ImagenModels imagenSave = new ImagenModels();
        imagenSave.setImagen(imagen);
        imagenSave.setFechaSubida(new Date());
        imagenSave.setUsuario(user);
        return imagenesRepository.save(imagenSave);
    }

    public void borrarImagen(int idImagen) {
        if (!imagenesRepository.existsById(idImagen)) {
            throw new ApiException(404, Constantes.ERROR_NO_EXISTE);
        } else {
            imagenesRepository.deleteById(idImagen);
        }
    }

}
