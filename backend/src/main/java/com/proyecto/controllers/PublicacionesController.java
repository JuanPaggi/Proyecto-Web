package com.proyecto.controllers;

import com.proyecto.dtos.PublicationResponseDto;
import com.proyecto.dtos.PublicationCreateDto;
import com.proyecto.services.PublicacionesService;
import com.proyecto.utils.ApiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Capa de controlador para las publicaciones.
 * Donde se reciben todas las peticiones Rest.
 */

@RestController
@RequestMapping("/publicaciones")
public class PublicacionesController {

    public static final Logger log = LoggerFactory.getLogger(PublicacionesController.class);

    @Autowired
    PublicacionesService publicacionesService;

    @GetMapping("")
    public ResponseEntity<PublicationResponseDto> obtenerPublicacion(@RequestParam(name = "id_publicacion") Integer idPublicacion) {
        try {
            PublicationResponseDto salida = publicacionesService.obetenerPublicacion(idPublicacion);
            return new ResponseEntity<>(salida, HttpStatus.OK);
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

    @PostMapping("")
    public ResponseEntity<Integer> crearPublicacion(@RequestBody PublicationCreateDto body, HttpServletRequest request) {
        try {
            Integer salida = publicacionesService.crearPublicacion(body, request);
            return new ResponseEntity<>(salida, HttpStatus.OK);
        } catch (ApiException error) {
            switch (error.getCode()) {
                case 400:
                    log.error("ERROR: " + error.getMessage());
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                case 404:
                    log.error("ERROR: " + error.getMessage());
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                default:
                    log.error(error.getMessage(), error);
                    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @DeleteMapping("")
    public ResponseEntity<Void> borrarPublicacion(@RequestParam(name = "id_publicacion") Integer idPublicacion) {
        try {
            publicacionesService.borrarPublicacion(idPublicacion);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ApiException error) {
            switch (error.getCode()) {
                case 404:
                    log.error("ERROR :" + error.getMessage());
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                default:
                    log.error("ERROR :" + error.getMessage(), error);
                    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

            }
        }
    }

    @PutMapping("")
    public ResponseEntity<Integer> actualizarPublicacion(@RequestParam(name = "id_publicacion") Integer idPublicacion, @RequestBody PublicationCreateDto body) {
        try {
            int salida = publicacionesService.actualizarPublicacion(idPublicacion, body);
            return new ResponseEntity<>(salida, HttpStatus.OK);
        } catch (ApiException error) {
            switch (error.getCode()) {
                case 404:
                    log.error("ERROR: " + error.getMessage());
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                default:
                    log.error(error.getMessage(), error);
                    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

}
