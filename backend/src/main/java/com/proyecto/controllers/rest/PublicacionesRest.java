package com.proyecto.controllers.rest;

import com.proyecto.dtos.publication.PublicationCreateDto;
import com.proyecto.dtos.publication.PublicationResponseDto;
import com.proyecto.dtos.ResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequestMapping("/publicaciones")
public interface PublicacionesRest {

    @GetMapping("/{id_publicacion}")
    ResponseEntity<PublicationResponseDto> obtenerPublicacion(@PathVariable("id_publicacion") Integer idPublicacion);

    @GetMapping("/todas")
    ResponseEntity<List<PublicationResponseDto>> obtenerTodasPublicacion();

    @PostMapping("")
    ResponseEntity<ResponseDto> crearPublicacion(@Validated @RequestBody PublicationCreateDto body, HttpServletRequest request);

    @DeleteMapping("/{id_publicacion}")
    ResponseEntity<ResponseDto> borrarPublicacion(@PathVariable("id_publicacion") Integer idPublicacion, HttpServletRequest request);

    @PutMapping("/{id_publicacion}")
    ResponseEntity<ResponseDto> actualizarPublicacion(@PathVariable("id_publicacion") Integer idPublicacion, @RequestBody PublicationCreateDto body, HttpServletRequest request);

}
