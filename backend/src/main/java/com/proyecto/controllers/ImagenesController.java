package com.proyecto.controllers;

import com.proyecto.services.ImagenesService;
import com.proyecto.utils.ApiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Capa de controlador para las imagenes.
 * Donde se reciben todas las peticiones Rest.
 */

@RestController
@RequestMapping("imagenes")
public class ImagenesController {

    public static final Logger log = LoggerFactory.getLogger(ImagenesController.class);

    @Autowired
    ImagenesService imagenesService;

    @GetMapping(value = "", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody
    ResponseEntity<byte[]> obtenerImagen(@RequestParam(name = "id_imagen") Integer idImagen) {
        try {
            return new ResponseEntity<>(imagenesService.obtenerBytePorId(idImagen), HttpStatus.OK);
        } catch (ApiException error) {
            switch (error.getCode()) {
                case 404:
                    log.error("ERROR :" + error.getMessage());
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                default:
                    log.error(error.getMessage(), error);
                    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

}