package com.proyecto.controllers.rest;

import com.proyecto.dtos.publication.PublicationCreateDto;
import com.proyecto.dtos.publication.PublicationResponseDto;
import com.proyecto.dtos.ResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/publicaciones")
public interface PublicacionesRest {

    @GetMapping("")
    ResponseEntity<PublicationResponseDto> obtenerPublicacion(@RequestParam(name = "id_publicacion") Integer idPublicacion);

    @PostMapping("")
    ResponseEntity<ResponseDto> crearPublicacion(@Validated @RequestBody PublicationCreateDto body, HttpServletRequest request);

    @DeleteMapping("")
    ResponseEntity<ResponseDto> borrarPublicacion(@RequestParam(name = "id_publicacion") Integer idPublicacion, HttpServletRequest request);

    @PutMapping("")
    ResponseEntity<ResponseDto> actualizarPublicacion(@Validated @RequestParam(name = "id_publicacion") Integer idPublicacion, @RequestBody PublicationCreateDto body, HttpServletRequest request);

}
