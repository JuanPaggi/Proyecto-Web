package com.proyecto.services;

import com.proyecto.dtos.ComentarioDto;
import com.proyecto.dtos.GetComentarioDto;
import com.proyecto.models.ComentarioModels;
import com.proyecto.repository.ComentariosRepository;
import com.proyecto.utils.ApiException;
import com.proyecto.utils.Constantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class ComentariosService {

    @Autowired
    ComentariosRepository comentariosRepository; //uso el repository porque neeito acceder a la base de datos


    //aca me fijo en la base de datos si estan presentes los valores dentro de comentario.

    public GetComentarioDto obtenerComentario(int idComentario) {

        try {

            Optional<ComentarioModels> comentario = comentariosRepository.findById(idComentario);

            if (comentario.isPresent()) {
                GetComentarioDto salida = new GetComentarioDto();
                salida.setIdComentario(comentario.get().getIdComentario());
                salida.setTexto(comentario.get().getTexto());
                salida.setFechaCreacion(comentario.get().getFechaCreacion());

                return salida;
            } else {
                throw new ApiException(404, "El comentario no existe.");
            }

        } catch (ApiException error) {
            throw error;
        } catch (Exception error) {
            throw new ApiException(500, Constantes.ERROR_GENERAL);
        }
    }

    public Integer crearComentario(ComentarioDto entrada) {
        try {

            if (entrada.getTexto().length() <= 8000) {
                ComentarioModels comentario = new ComentarioModels();
                comentario.setTexto(entrada.getTexto());
                comentario.setFechaCreacion(new Date());

                comentario = comentariosRepository.save(comentario);

                return comentario.getIdComentario();
            } else {
                throw new ApiException(400, "los datos enviados no son validos");
            }
        } catch (ApiException error) {
            throw error;
        } catch (Exception error) {
            throw new ApiException(500, Constantes.ERROR_GENERAL);
        }
    }
}
