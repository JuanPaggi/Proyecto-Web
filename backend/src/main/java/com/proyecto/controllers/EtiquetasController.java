package com.proyecto.controllers;

import com.proyecto.dtos.GetEtiquetaDto;
import com.proyecto.dtos.EtiquetaDto;
import com.proyecto.services.EtiquetasService;
import com.proyecto.utils.ApiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



/**
 * Capa de controlador.
 * Donde se reciben todas las peticiones Rest.
 */

@RestController
@RequestMapping("/etiquetas")
public class EtiquetasController {

    public static final Logger log = LoggerFactory.getLogger(EtiquetasController.class);

    @Autowired
    EtiquetasService etiquetasService;

    /**
     * Se recibe un ID y se obtiene la informacion de la etiqueta.
     */

    @GetMapping("")
    public ResponseEntity<GetEtiquetaDto> obtenerEtiqueta(@RequestParam Integer idEtiqueta) {
        try {
            GetEtiquetaDto salida = etiquetasService.obtenerEtiqueta(idEtiqueta);
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

    /**
     * Se recibe la informacion necesaria para crear un Etiqueta.
     */

    @PostMapping("")
    public ResponseEntity<Integer> crearEtiqueta(@RequestBody EtiquetaDto body) {
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

    /**
     * Se recibe el idEtiqueta de la etiqueta que quiero eliminar
     */

    @DeleteMapping("")
    public ResponseEntity<Void> borrarEtiqueta(@RequestParam Integer idEtiqueta) {
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
    public ResponseEntity<Integer> actualziarEtiqueta(@RequestParam Integer idEtiqueta, @RequestBody EtiquetaDto body) {
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
