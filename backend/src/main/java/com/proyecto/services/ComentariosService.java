package com.proyecto.services;

import com.proyecto.dtos.CommentCreateDto;
import com.proyecto.dtos.CommentResponseDto;
import com.proyecto.models.ComentarioModels;
import com.proyecto.models.PublicacionModels;
import com.proyecto.repository.ComentariosRepository;
import com.proyecto.repository.PublicacionesRepository;
import com.proyecto.utils.ApiException;
import com.proyecto.utils.Constantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.Optional;

/**
 * Capa de servicio para los comentarios.
 */

@Service
public class ComentariosService {

    @Autowired
    ComentariosRepository comentariosRepository;

    @Autowired
    PublicacionesRepository publicacionesRepository;

    public CommentResponseDto obtenerComentario(int idComentario) {
        try {
            Optional<ComentarioModels> comentario = comentariosRepository.findById(idComentario);
            if (comentario.isPresent()) {
                CommentResponseDto salida = new CommentResponseDto();
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

    public Integer crearComentario(CommentCreateDto entrada) {
        try {
            if (entrada.getTexto().length() <= 8000) {
                ComentarioModels comentario = new ComentarioModels();
                comentario.setTexto(entrada.getTexto());
                comentario.setFechaCreacion(new Date());
                Optional<PublicacionModels> publicacion = publicacionesRepository.findById(entrada.getIdPublicacion());
                if (publicacion.isPresent()) {
                    comentario.setPublicacion(publicacion.get());
                } else {
                    throw new ApiException(404, Constantes.ERROR_PUBLICACIONES_NOEXISTE);
                }
                comentario = comentariosRepository.save(comentario);
                return comentario.getIdComentario();
            } else {
                throw new ApiException(400, Constantes.ERROR_DATOS_INVALIDOS);
            }
        } catch (ApiException error) {
            throw error;
        } catch (Exception error) {
            throw new ApiException(500, Constantes.ERROR_GENERAL);
        }
    }

    public void borrarComentario(int idComentario) {
        try {
            if (!publicacionesRepository.existsById(idComentario)) {
                throw new ApiException(404, "El comentario no existe");
            } else {
                comentariosRepository.deleteById(idComentario);
            }
        } catch (ApiException error) {
            throw error;
        } catch (Exception error) {
            throw new ApiException(500, Constantes.ERROR_GENERAL);
        }
    }

    public int actualizarComentario(int idComentario, CommentCreateDto body) {
        try {
            Optional<ComentarioModels> textoDB = comentariosRepository.findById(idComentario);
            if (textoDB.isPresent()) {
                ComentarioModels entrada = textoDB.get();
                entrada.setTexto(body.getTexto());
                comentariosRepository.save(entrada);
                return entrada.getIdComentario();
            } else {
                throw new ApiException(404, Constantes.ERROR_PUBLICACIONES_NOEXISTE);
            }
        } catch (ApiException error) {
            throw error;
        } catch (Exception error) {
            throw new ApiException(500, Constantes.ERROR_GENERAL);
        }
    }

}

