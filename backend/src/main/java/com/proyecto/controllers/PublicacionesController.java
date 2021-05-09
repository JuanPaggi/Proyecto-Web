package com.proyecto.controllers;


import com.proyecto.dtos.GetPublicacionDto;
import com.proyecto.dtos.PostPublicacionDto;
import com.proyecto.services.PublicacionesService;
import com.proyecto.utils.ApiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/publicaciones")
public class PublicacionesController {

    public static final Logger log = LoggerFactory.getLogger(PublicacionesController.class);

    @Autowired
    PublicacionesService publicacionesService;

    @GetMapping("")
    public ResponseEntity<GetPublicacionDto> obtenerPublicacion(@RequestParam String idPublicacion) {
        try {
            GetPublicacionDto salida = publicacionesService.obetenerPublicacion(Integer.parseInt(idPublicacion));
            return new ResponseEntity<>(salida, HttpStatus.OK);
        } catch (ApiException error) {
            switch (error.getCode()) {
                case 404:
                    log.error("ERROR :" + error.getMessage());
                    return new ResponseEntity<>((HttpStatus.NOT_FOUND));
                default:
                    log.error(error.getMessage(), error);
                    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @PostMapping("")
    public ResponseEntity<Integer> crearPublicacion(@RequestBody PostPublicacionDto body) {
        try{
            Integer salida = publicacionesService.crearPublicacion(body);
            return new ResponseEntity<>(salida, HttpStatus.OK);
        } catch (ApiException error) {
            switch (error.getCode()) {
                case 400:
                    log.error("ERROR: " + error.getMessage());
                    return new ResponseEntity<>(HttpStatus.CONFLICT);
                default:
                    log.error(error.getMessage(), error);
                    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

}
