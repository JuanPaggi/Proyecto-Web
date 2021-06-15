package com.proyecto.controllers.rest;

import com.proyecto.dtos.comment.CommentCreateDto;
import com.proyecto.dtos.comment.CommentResponseDto;
import com.proyecto.dtos.ResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/comentarios")
public interface ComentariosRest {

    @GetMapping("/{id_comentario}")
    ResponseEntity<CommentResponseDto> obtenerComentario(@PathVariable("id_comentario") Integer idComentario);

    @PostMapping("")
    ResponseEntity<Integer> crearComentario(@Validated @RequestBody CommentCreateDto body, HttpServletRequest request);

    @DeleteMapping("")
    ResponseEntity<ResponseDto> borrarComentario(@RequestParam(name = "id_comentario") Integer idComentario, HttpServletRequest request);

    @PutMapping("")
    ResponseEntity<ResponseDto> actualizarComentario(@Validated @RequestParam(name = "id_comentario") Integer idComentario, @RequestBody CommentCreateDto body, HttpServletRequest request);

}
