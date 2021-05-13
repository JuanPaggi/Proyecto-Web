package com.proyecto.services;

import com.proyecto.dtos.GetPublicacionDto;
import com.proyecto.dtos.PostPublicacionDto;
import com.proyecto.models.PublicacionModels;
import com.proyecto.repository.PublicacionesRepository;
import com.proyecto.utils.ApiException;
import com.proyecto.utils.Constantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import java.util.Date;
import java.util.Optional;

@Service
public class PublicacionesService {

    @Autowired
    PublicacionesRepository publicacionesRepository;

    public GetPublicacionDto obetenerPublicacion(int idPublicacion) {

        try {

            Optional<PublicacionModels> publicacion = publicacionesRepository.obtenerPublicacion(idPublicacion);

            if (publicacion.isPresent()) {
                GetPublicacionDto salida = new GetPublicacionDto();
                salida.setIdPublicacion(publicacion.get().getIdPublicacion());
                salida.setDescripcion(publicacion.get().getDescripcion());
                salida.setFechaCreacion(publicacion.get().getFechaCreacion());
                salida.setTitulo(publicacion.get().getTitulo());
                return salida;
            } else {
                throw new ApiException(404, "La publicacion no existe.");
            }

        } catch (ApiException error) {
            throw error;
        } catch (Exception error) {
            throw new ApiException(500, Constantes.ERROR_GENERAL);
        }
    }


    public Integer crearPublicacion(PostPublicacionDto entrada) {

        try {

            if (entrada.getDescripcion().length() <= 10000) {

                PublicacionModels publicacion = new PublicacionModels();
                publicacion.setDescripcion(entrada.getDescripcion());
                publicacion.setFechaCreacion(new Date()); //crea la fecha en el momento
                publicacion.setTitulo(entrada.getTitulo());
                publicacion = publicacionesRepository.save(publicacion);

                return publicacion.getIdPublicacion();
            } else {
                throw new ApiException(400, "los datos enviados no son validos");
            }
        } catch (ApiException error) {
            throw error;
        } catch (Exception error) {
            throw new ApiException(500, Constantes.ERROR_GENERAL);
        }
    }

    public void borrarPublicacion(int idPublicacion) {

        try {
            if (!publicacionesRepository.existsById(idPublicacion)){
                throw new ApiException(404, "La publicacion no existe.");
            } else {
                publicacionesRepository.deleteById(idPublicacion);
            }
        } catch (ApiException error) {
            throw error;
        } catch (Exception error) {
            throw new ApiException(500, Constantes.ERROR_GENERAL);
        }

    }

}
