package com.proyecto.controllers;


import com.proyecto.dtos.ComentarioDto;
import com.proyecto.dtos.GetComentarioDto;
import com.proyecto.services.ComentariosService;
import com.proyecto.utils.ApiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/comentarios")
public class ComentariosController {

    public static final Logger log = LoggerFactory.getLogger(ComentariosController.class);

    @Autowired
    ComentariosService comentariosService;


    @GetMapping("")
    public ResponseEntity<GetComentarioDto> obtenerComentario(@RequestParam Integer idComentario) {
        try {

            GetComentarioDto salida = comentariosService.obtenerComentario(idComentario);
            return new ResponseEntity<>(salida, HttpStatus.OK);

        } catch (ApiException error) {
            switch (error.getCode()) {
                case 404:
                    log.error("ERROR: " + error.getMessage());
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                default:
                    log.error("ERROR: " + error.getMessage(), error);
                    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @PostMapping("")
    public ResponseEntity<Integer> crearComentario(@RequestBody ComentarioDto body) {
        try {
            Integer salida = comentariosService.crearComentario(body);
            return new ResponseEntity<>(salida, HttpStatus.OK);
        } catch (ApiException error) {
            switch (error.getCode()) {
                case 400:
                    log.error("ERROR : " + error.getMessage());
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                case 404:
                    log.error("ERROR : " + error.getMessage());
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                default:
                    log.error(error.getMessage(), error);
                    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

            }

        }
    }
}
