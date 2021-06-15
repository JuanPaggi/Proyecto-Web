package com.proyecto.services;

import com.proyecto.dtos.comment.CommentCreateDto;
import com.proyecto.dtos.comment.CommentResponseDto;
import com.proyecto.dtos.user.UserNameResponseDto;
import com.proyecto.models.ComentarioModels;
import com.proyecto.models.PublicacionModels;
import com.proyecto.models.UsuarioModels;
import com.proyecto.repository.ComentariosRepository;
import com.proyecto.repository.PublicacionesRepository;
import com.proyecto.repository.UsuariosRepository;
import com.proyecto.exceptions.ApiException;
import com.proyecto.utils.Constantes;
import com.proyecto.utils.Validaciones;
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

            UserNameResponseDto userCommentDatos = new UserNameResponseDto();
            userCommentDatos.setUser(comentario.get().getUsuario().getUser());
            userCommentDatos.setIdUsuario(comentario.get().getUsuario().getIdUsuario());
            salida.setUser(userCommentDatos);

            return salida;
        } else {
            throw new ApiException(404, Constantes.ERROR_NO_EXISTE);
        }
    }

    public Integer crearComentario(CommentCreateDto entrada, HttpServletRequest request) {
        String userInput = Validaciones.obtenerUserLogin(request);
        if (entrada.getTexto().length() <= 3000) {
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
            return comentariosRepository.save(comentario).getIdComentario();
        } else {
            throw new ApiException(400, Constantes.ERROR_DATOS_INVALIDOS);
        }
    }

    public void borrarComentario(int idComentario, HttpServletRequest request) {
        String userInput = Validaciones.obtenerUserLogin(request);
        if (!comentariosRepository.existByIdUser(idComentario, userInput).isPresent()) {
            throw new ApiException(404, Constantes.ERROR_NO_EXISTE);
        } else {
            comentariosRepository.deleteById(idComentario);
        }
    }

    public void actualizarComentario(int idComentario, CommentCreateDto body, HttpServletRequest request) {
        String userInput = Validaciones.obtenerUserLogin(request);
        Optional<ComentarioModels> textoDB = comentariosRepository.existByIdUser(idComentario, userInput);
        if (textoDB.isPresent()) {
            textoDB.get().setTexto(body.getTexto());
            comentariosRepository.save(textoDB.get());
        } else {
            throw new ApiException(404, Constantes.ERROR_NO_EXISTE);
        }
    }

}

