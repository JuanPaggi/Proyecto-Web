package com.proyecto.services;

import com.proyecto.dtos.comment.CommentCreateDto;
import com.proyecto.dtos.comment.CommentResponseDto;
import com.proyecto.models.ComentarioModels;
import com.proyecto.models.PublicacionModels;
import com.proyecto.models.UsuarioModels;
import com.proyecto.repository.ComentariosRepository;
import com.proyecto.repository.PublicacionesRepository;
import com.proyecto.repository.UsuariosRepository;
import com.proyecto.exceptions.ApiException;
import com.proyecto.utils.Constantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
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

    @Autowired
    UsuariosRepository usuariosRepository;

    public CommentResponseDto obtenerComentario(int idComentario) {
        Optional<ComentarioModels> comentario = comentariosRepository.findById(idComentario);
        if (comentario.isPresent()) {
            CommentResponseDto salida = new CommentResponseDto();
            salida.setIdComentario(comentario.get().getIdComentario());
            salida.setTexto(comentario.get().getTexto());
            salida.setFechaCreacion(comentario.get().getFechaCreacion());
            return salida;
        } else {
            throw new ApiException(404, Constantes.ERROR_NO_EXISTE);
        }
    }

    public Integer crearComentario(CommentCreateDto entrada, HttpServletRequest request) {
        String userInput = "";
        if (request.getSession(false) != null) {
            userInput = (String) request.getSession(false).getAttribute("user");
        } else {
            throw new ApiException(401, Constantes.ERROR_NO_AUTORIZADO);
        }
        if (entrada.getTexto().length() <= 8000) {
            ComentarioModels comentario = new ComentarioModels();
            comentario.setTexto(entrada.getTexto());
            comentario.setFechaCreacion(new Date());
            Optional<UsuarioModels> userDB = usuariosRepository.obtenerUsuario(userInput);
            if (userDB.isPresent()) {
                comentario.setUsuario(userDB.get());
            } else {
                throw new ApiException(404, Constantes.ERROR_NO_EXISTE);
            }
            Optional<PublicacionModels> publicacion = publicacionesRepository.findById(entrada.getIdPublicacion());
            if (publicacion.isPresent()) {
                comentario.setPublicacion(publicacion.get());
            } else {
                throw new ApiException(404, Constantes.ERROR_NO_EXISTE);
            }
            comentario = comentariosRepository.save(comentario);
            return comentario.getIdComentario();
        } else {
            throw new ApiException(400, Constantes.ERROR_DATOS_INVALIDOS);
        }
    }

    public void borrarComentario(int idComentario) {
        if (!publicacionesRepository.existsById(idComentario)) {
            throw new ApiException(404, Constantes.ERROR_NO_EXISTE);
        } else {
            comentariosRepository.deleteById(idComentario);
        }
    }

    public int actualizarComentario(int idComentario, CommentCreateDto body) {
        Optional<ComentarioModels> textoDB = comentariosRepository.findById(idComentario);
        if (textoDB.isPresent()) {
            ComentarioModels entrada = textoDB.get();
            entrada.setTexto(body.getTexto());
            comentariosRepository.save(entrada);
            return entrada.getIdComentario();
        } else {
            throw new ApiException(404, Constantes.ERROR_NO_EXISTE);
        }
    }

}

