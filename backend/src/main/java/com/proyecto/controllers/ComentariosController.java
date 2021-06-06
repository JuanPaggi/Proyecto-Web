package com.proyecto.controllers;

import com.proyecto.controllers.rest.ComentariosRest;
import com.proyecto.dtos.CommentCreateDto;
import com.proyecto.dtos.CommentResponseDto;
import com.proyecto.dtos.ResponseDto;
import com.proyecto.services.ComentariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Capa de controlador para los comentarios.
 * Donde se reciben todas las peticiones Rest.
 */

@RestController
public class ComentariosController implements ComentariosRest {

    @Autowired
    ComentariosService comentariosService;

    public ResponseEntity<CommentResponseDto> obtenerComentario(Integer idComentario) {
        return new ResponseEntity<>(comentariosService.obtenerComentario(idComentario), HttpStatus.OK);
    }

    public ResponseEntity<ResponseDto> crearComentario(CommentCreateDto body, HttpServletRequest request) {
        comentariosService.crearComentario(body, request);
        return new ResponseEntity<>(ResponseDto.getInstanceOk(), HttpStatus.OK);
    }

    public ResponseEntity<ResponseDto> borrarComentario(Integer idComentario) {
        comentariosService.borrarComentario(idComentario);
        return new ResponseEntity<>(ResponseDto.getInstanceOk(), HttpStatus.OK);
    }

    public ResponseEntity<ResponseDto> actualizarComentario(Integer idComentario, CommentCreateDto body) {
        comentariosService.actualizarComentario(idComentario, body);
        return new ResponseEntity<>(ResponseDto.getInstanceOk(), HttpStatus.OK);
    }

}
