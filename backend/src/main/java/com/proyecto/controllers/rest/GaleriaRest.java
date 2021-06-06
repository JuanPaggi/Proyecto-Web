package com.proyecto.controllers.rest;

import com.proyecto.dtos.GalleryResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/galerias")
public interface GaleriaRest {

    @GetMapping("")
    ResponseEntity<GalleryResponseDto> obtenerGaleria(@RequestParam Integer idGaleria);


}
