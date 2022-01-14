package com.proyecto.controllers.rest;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("")
public interface ImagenesRest {

    @GetMapping(value = "/image/{fileID}/{imageSEO}", produces = MediaType.IMAGE_JPEG_VALUE)
    ResponseEntity<byte[]> obtenerImagen(@PathVariable("fileID") Integer fileID);

}
