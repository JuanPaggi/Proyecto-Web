package com.proyecto.controllers.rest;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("imagenes")
public interface ImagenesRest {

    @GetMapping(value = "", produces = MediaType.IMAGE_JPEG_VALUE)
    ResponseEntity<byte[]> obtenerImagen(@RequestParam(name = "id_imagen") Integer idImagen);

}
