package com.proyecto.controllers;

import com.proyecto.controllers.rest.ImagenesRest;
import com.proyecto.services.ImagenesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Capa de controlador para las imagenes.
 * Donde se reciben todas las peticiones Rest.
 */

@RestController
public class ImagenesController implements ImagenesRest {

    @Autowired
    ImagenesService imagenesService;

    public ResponseEntity<byte[]> obtenerImagen(Integer idImagen) {
            return new ResponseEntity<>(imagenesService.obtenerBytePorId(idImagen), HttpStatus.OK);
    }

}