package com.proyecto.controllers.rest;

import com.proyecto.dtos.ResponseDto;
import com.proyecto.dtos.gallery.GalleryCreateDto;
import com.proyecto.dtos.gallery.GalleryResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@RequestMapping("/galerias")
public interface GaleriaRest {

    @GetMapping("")
    ResponseEntity<GalleryResponseDto> obtenerGaleria(@RequestParam(name = "id_galeria") Integer idGaleria);

    @GetMapping("/all")
    ResponseEntity<List<GalleryResponseDto>> getAll(HttpServletRequest request);

    @PostMapping("")
    ResponseEntity<ResponseDto> crearGaleria(@RequestBody GalleryCreateDto body, HttpServletRequest request) throws NoSuchAlgorithmException;

    @DeleteMapping("")
    ResponseEntity<ResponseDto> borrarGaleria(@RequestParam(name = "id_galeria") Integer idGaleria, HttpServletRequest request);

}
