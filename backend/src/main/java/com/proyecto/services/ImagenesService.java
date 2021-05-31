package com.proyecto.services;

import com.proyecto.models.ImagenModels;
import com.proyecto.repository.ImagenesRepository;
import com.proyecto.utils.ApiException;
import com.proyecto.utils.Constantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        try {
            Optional<ImagenModels> imagen = imagenesRepository.findById(idImagen);
            if (imagen.isPresent()) {
                return imagen.get().getImagen();
            } else {
                throw new ApiException(404, "La imagen no existe.");
            }
        } catch (ApiException error) {
            throw error;
        } catch (Exception error) {
            throw new ApiException(500, Constantes.ERROR_GENERAL);
        }
    }

    public ImagenModels obtenerImagenPorId(int idImagen) {
        try {
            Optional<ImagenModels> imagen = imagenesRepository.findById(idImagen);
            if (imagen.isPresent()) {
                return imagen.get();
            } else {
                throw new ApiException(404, "No existe la imagen");
            }
        } catch (ApiException error) {
            throw error;
        } catch (Exception error) {
            throw new ApiException(500, Constantes.ERROR_GENERAL);
        }
    }

    public Optional<ImagenModels> obtenerImagenPorHash(byte[] hash) {
        try {
            Optional<ImagenModels> imagenDB = imagenesRepository.findByHash(hash);
            return imagenDB;
        } catch (ApiException error) {
            throw error;
        } catch (Exception error) {
            throw new ApiException(500, Constantes.ERROR_GENERAL);
        }
    }

    public ImagenModels cargarImagen(byte[] imagen) {
        try {
            ImagenModels imagenSave = new ImagenModels();
            imagenSave.setImagen(imagen);
            imagenSave.setFechaSubida(new Date());
            return imagenesRepository.save(imagenSave);
        } catch (Exception error) {
            throw new ApiException(500, "Error al cargar imagen");
        }
    }

    public void borrarImagen(int idImagen) {
        try {
            if (!imagenesRepository.existsById(idImagen)) {
                throw new ApiException(404, "La Imagen no existe");
            } else {
                imagenesRepository.deleteById(idImagen);
            }
        } catch (ApiException error) {
            throw error;
        } catch (Exception error) {
            throw new ApiException(500, Constantes.ERROR_GENERAL);
        }
    }

}
