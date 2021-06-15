package com.proyecto.services;

import com.proyecto.dtos.comment.CommentResponseDto;
import com.proyecto.dtos.publication.PublicationCreateDto;
import com.proyecto.dtos.publication.PublicationResponseDto;
import com.proyecto.dtos.tag.TagResponseDto;
import com.proyecto.dtos.user.UserNameResponseDto;
import com.proyecto.models.ComentarioModels;
import com.proyecto.models.EtiquetaModels;
import com.proyecto.models.PublicacionModels;
import com.proyecto.models.UsuarioModels;
import com.proyecto.repository.EtiquetasRepository;
import com.proyecto.repository.PublicacionesRepository;
import com.proyecto.repository.UsuariosRepository;
import com.proyecto.exceptions.ApiException;
import com.proyecto.utils.Constantes;
import com.proyecto.utils.Validaciones;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Capa de servicio para las publicaciones.
 */

@Service
public class PublicacionesService {

    @Autowired
    PublicacionesRepository publicacionesRepository;

    @Autowired
    EtiquetasRepository etiquetasRepository;

    @Autowired
    UsuariosRepository usuariosRepository;

    public List<PublicationResponseDto> obtenerTodasPublicaciones() {
        List<PublicacionModels> publicaciones = publicacionesRepository.findAll();
        List<PublicationResponseDto> salida = new ArrayList<>();
        for (PublicacionModels it : publicaciones) {
            PublicationResponseDto dto = new PublicationResponseDto();
            dto.setIdPublicacion(it.getIdPublicacion());
            dto.setDescripcion(it.getDescripcion());
            dto.setFechaCreacion(it.getFechaCreacion());
            dto.setTitulo(it.getTitulo());
            UserNameResponseDto userDatos = new UserNameResponseDto();
            userDatos.setUser(it.getUsuario().getUser());
            userDatos.setIdUsuario(it.getUsuario().getIdUsuario());
            dto.setUsuario(userDatos);
            List<TagResponseDto> etiquetas = new ArrayList<>();
            for (EtiquetaModels it1 : it.getEtiquetas()) {
                TagResponseDto etiquetaDto = new TagResponseDto();
                etiquetaDto.setIdEtiqueta(it1.getIdEtiqueta());
                etiquetaDto.setEtiqueta(it1.getEtiqueta());
                etiquetas.add(etiquetaDto);
            }
            dto.setEtiquetas(etiquetas);
            List<CommentResponseDto> comentarios = new ArrayList<>();
            for (ComentarioModels it1 : it.getComentarios()) {
                CommentResponseDto comentarioDto = new CommentResponseDto();
                comentarioDto.setIdComentario(it1.getIdComentario());
                comentarioDto.setTexto(it1.getTexto());
                comentarioDto.setFechaCreacion(it1.getFechaCreacion());

                UserNameResponseDto userCommentDatos = new UserNameResponseDto();
                userCommentDatos.setUser(it1.getUsuario().getUser());
                userCommentDatos.setIdUsuario(it1.getUsuario().getIdUsuario());
                comentarioDto.setUser(userCommentDatos);

                comentarios.add(comentarioDto);
            }
            dto.setComentarios(comentarios);

            salida.add(dto);
        }
        return salida;
    }

    public PublicationResponseDto obetenerPublicacion(int idPublicacion) {
        Optional<PublicacionModels> publicacion = publicacionesRepository.findById(idPublicacion);
        if (publicacion.isPresent()) {
            PublicationResponseDto salida = new PublicationResponseDto();
            salida.setIdPublicacion(publicacion.get().getIdPublicacion());
            salida.setDescripcion(publicacion.get().getDescripcion());
            salida.setFechaCreacion(publicacion.get().getFechaCreacion());
            salida.setTitulo(publicacion.get().getTitulo());
            UserNameResponseDto userDatos = new UserNameResponseDto();
            userDatos.setUser(publicacion.get().getUsuario().getUser());
            userDatos.setIdUsuario(publicacion.get().getUsuario().getIdUsuario());
            salida.setUsuario(userDatos);
            List<TagResponseDto> etiquetas = new ArrayList<>();
            for (EtiquetaModels it : publicacion.get().getEtiquetas()) {
                TagResponseDto etiquetaDto = new TagResponseDto();
                etiquetaDto.setIdEtiqueta(it.getIdEtiqueta());
                etiquetaDto.setEtiqueta(it.getEtiqueta());
                etiquetas.add(etiquetaDto);
            }
            salida.setEtiquetas(etiquetas);
            List<CommentResponseDto> comentarios = new ArrayList<>();
            for (ComentarioModels it : publicacion.get().getComentarios()) {
                CommentResponseDto comentarioDto = new CommentResponseDto();
                comentarioDto.setIdComentario(it.getIdComentario());
                comentarioDto.setTexto(it.getTexto());
                comentarioDto.setFechaCreacion(it.getFechaCreacion());
                comentarios.add(comentarioDto);
            }
            salida.setComentarios(comentarios);
            return salida;
        } else {
            throw new ApiException(404, Constantes.ERROR_NO_EXISTE);
        }
    }

    public void crearPublicacion(PublicationCreateDto entrada, HttpServletRequest request) {
        String userInput = Validaciones.obtenerUserLogin(request);
        if (entrada.getDescripcion().length() <= 10000) {
            PublicacionModels publicacion = new PublicacionModels();
            publicacion.setDescripcion(entrada.getDescripcion());
            publicacion.setFechaCreacion(new Date());
            publicacion.setTitulo(entrada.getTitulo());
            Optional<UsuarioModels> userDB = usuariosRepository.obtenerUsuario(userInput);
            if (userDB.isPresent()) {
                publicacion.setUsuario(userDB.get());
            } else {
                throw new ApiException(404, Constantes.ERROR_NO_EXISTE);
            }
            if (entrada.getEtiquetas() != null) {
                List<EtiquetaModels> etiquetas = etiquetasRepository.findAllById(entrada.getEtiquetas());
                if (etiquetas.size() != entrada.getEtiquetas().size()) {
                    throw new ApiException(404, "Alguna de las etiquetas recibidas no exite");
                }
                publicacion.setEtiquetas(etiquetas);
            }
            publicacionesRepository.save(publicacion);
        } else {
            throw new ApiException(400, Constantes.ERROR_DATOS_INVALIDOS);
        }
    }

    public void borrarPublicacion(int idPublicacion, HttpServletRequest request) {
        String userInput = Validaciones.obtenerUserLogin(request);
        if (!publicacionesRepository.existByIdUser(idPublicacion, userInput).isPresent()) {
            throw new ApiException(404, Constantes.ERROR_NO_EXISTE);
        } else {
            publicacionesRepository.deleteById(idPublicacion);
        }
    }

    public void actualizarPublicacion(int idPublicacion, PublicationCreateDto body, HttpServletRequest request) {
        String userInput = Validaciones.obtenerUserLogin(request);
        Optional<PublicacionModels> publicacion = publicacionesRepository.existByIdUser(idPublicacion, userInput);
        if (publicacion.isPresent()) {
            PublicacionModels entrada = publicacion.get();
            if (body.getTitulo() != null) {
                entrada.setTitulo(body.getTitulo());
            }
            if (body.getDescripcion() != null) {
                entrada.setDescripcion(body.getDescripcion());
            }
            List<EtiquetaModels> etiquetas = etiquetasRepository.findAllById(body.getEtiquetas());
            if (etiquetas.size() != body.getEtiquetas().size()) {
                throw new ApiException(404, "Alguna de las etiquetas recibidas no exite");
            }
            entrada.setEtiquetas(etiquetas);
            publicacionesRepository.save(entrada);
        } else {
            throw new ApiException(404, Constantes.ERROR_NO_EXISTE);
        }
    }

}
