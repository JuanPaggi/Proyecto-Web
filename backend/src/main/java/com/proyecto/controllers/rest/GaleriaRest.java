package com.proyecto.controllers.rest;

import com.proyecto.dtos.ResponseDto;
import com.proyecto.dtos.gallery.GalleryCreateDto;
import com.proyecto.dtos.gallery.GalleryResponseDto;
import com.proyecto.dtos.publication.PublicationCreateDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@RequestMapping("/galerias")
public interface GaleriaRest {

    @GetMapping("/{id_galeria}")
    ResponseEntity<GalleryResponseDto> obtenerGaleria(@PathVariable("id_galeria") Integer idGaleria);

    @GetMapping("/all")
    ResponseEntity<List<GalleryResponseDto>> getAll();

    @PostMapping("")
    ResponseEntity<ResponseDto> crearGaleria(@RequestBody GalleryCreateDto body, HttpServletRequest request) throws NoSuchAlgorithmException;

    @PutMapping("/{id_galeria}")
    ResponseEntity<ResponseDto> editarGaleria(@PathVariable("id_galeria") Integer idGaleria, @RequestBody GalleryCreateDto body, HttpServletRequest request) throws NoSuchAlgorithmException;

    @DeleteMapping("/{id_galeria}")
    ResponseEntity<ResponseDto> borrarGaleria(@PathVariable("id_galeria") Integer idPublicacion, HttpServletRequest request);

}
