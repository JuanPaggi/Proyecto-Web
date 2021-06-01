package com.proyecto.controllers;

import com.proyecto.dtos.TagResponseDto;
import com.proyecto.dtos.TagCreateDto;
import com.proyecto.services.EtiquetasService;
import com.proyecto.utils.ApiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Capa de controlador Para las etiquetas.
 * Donde se reciben todas las peticiones Rest.
 */

@RestController
@RequestMapping("/etiquetas")
public class EtiquetasController {

    public static final Logger log = LoggerFactory.getLogger(EtiquetasController.class);

    @Autowired
    EtiquetasService etiquetasService;

    @GetMapping("")
    public ResponseEntity<TagResponseDto> obtenerEtiqueta(@RequestParam(name = "id_etiqueta") Integer idEtiqueta) {
        try {
            TagResponseDto salida = etiquetasService.obtenerEtiqueta(idEtiqueta);
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

    @PostMapping("")
    public ResponseEntity<Integer> crearEtiqueta(@RequestBody TagCreateDto body) {
        try {
            Integer salida = etiquetasService.crearEtiqueta(body);
            return new ResponseEntity<>(salida, HttpStatus.OK);
        } catch (ApiException error) {
            switch (error.getCode()) {
                case 400:
                    log.error("ERROR: " + error.getMessage());
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                case 409:
                    log.error("ERROR: " + error.getMessage());
                    return new ResponseEntity<>(HttpStatus.CONFLICT);
                default:
                    log.error(error.getMessage(), error);
                    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @DeleteMapping("")
    public ResponseEntity<Void> borrarEtiqueta(@RequestParam(name = "id_etiqueta") Integer idEtiqueta) {
        try {
            etiquetasService.borrarEtiqueta(idEtiqueta);
            return new ResponseEntity<>(HttpStatus.OK);
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

    @PutMapping("")
    public ResponseEntity<Integer> actualziarEtiqueta(@RequestParam(name = "id_etiqueta") Integer idEtiqueta, @RequestBody TagCreateDto body) {
        try {
            int salida = etiquetasService.actualizarEtiqueta(idEtiqueta, body);
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
