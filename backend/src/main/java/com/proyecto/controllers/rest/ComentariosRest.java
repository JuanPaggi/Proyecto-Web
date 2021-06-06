package com.proyecto.controllers.rest;

import com.proyecto.dtos.CommentCreateDto;
import com.proyecto.dtos.CommentResponseDto;
import com.proyecto.dtos.ResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/comentarios")
public interface ComentariosRest {

    @GetMapping("")
    ResponseEntity<CommentResponseDto> obtenerComentario(@RequestParam(name = "id_comentario") Integer idComentario);

    @PostMapping("")
    ResponseEntity<ResponseDto> crearComentario(@Validated @RequestBody CommentCreateDto body, HttpServletRequest request);

    @DeleteMapping("")
    ResponseEntity<ResponseDto> borrarComentario(@RequestParam(name = "id_comentario") Integer idComentario);

    @PutMapping("")
    ResponseEntity<ResponseDto> actualizarComentario(@Validated @RequestParam(name = "id_comentario") Integer idComentario, @RequestBody CommentCreateDto body);

}
