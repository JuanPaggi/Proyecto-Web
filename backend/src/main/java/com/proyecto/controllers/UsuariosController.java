package com.proyecto.controllers;

import com.proyecto.dtos.GetUsuarioDto;
import com.proyecto.dtos.PutUsuarioDto;
import com.proyecto.dtos.PutUsuarioImagenDto;
import com.proyecto.dtos.UsuarioDto;
import com.proyecto.services.UsuariosService;
import com.proyecto.utils.ApiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/usuarios")
public class UsuariosController {

    public static final Logger log = LoggerFactory.getLogger(UsuariosController.class);

    @Autowired
    UsuariosService usuariosService;

    @GetMapping("")
    public ResponseEntity<GetUsuarioDto> obtenerUsuario(@RequestParam Integer idUsuario) {

        try {
            GetUsuarioDto salida = usuariosService.obtenerUsuario(idUsuario);
            return new ResponseEntity<>(salida, HttpStatus.OK);
        } catch (ApiException error) {
            switch (error.getCode()) {
                case 404:
                    log.error("ERROR : " + error.getMessage());
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                default:
                    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @PostMapping("")
    public ResponseEntity<Integer> crearUsuario(@RequestBody UsuarioDto body) {

        try {
            Integer usuario = usuariosService.crearUsuario(body);
            return new ResponseEntity<>(usuario, HttpStatus.OK);
        } catch (ApiException error) {
            switch (error.getCode()) {
                case 400:
                    log.error("ERROR : " + error.getMessage());
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                case 404:
                    log.error("ERROR : " + error.getMessage());
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                default:
                    log.error("ERROR : " + error.getMessage(), error);
                    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @DeleteMapping("")
    public ResponseEntity<Void> borrarUsuario(@RequestParam Integer idUsuario) {
        try {
            usuariosService.borrarUsuario(idUsuario);
            return new ResponseEntity<>(HttpStatus.OK);

        } catch (ApiException error) {
            switch (error.getCode()) {
                case 404:
                    log.error("ERROR : " + error.getMessage());
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                default:
                    log.error("ERROR :" + error.getMessage(), error);
                    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @PutMapping("")
    public ResponseEntity<Integer> actualizarUsuario(@RequestParam Integer idUsuario, @RequestBody PutUsuarioDto body) {
        try {
            int salida = usuariosService.actualizarUsuario(idUsuario, body);
            return new ResponseEntity<>(salida, HttpStatus.OK);

        } catch (ApiException error){
            switch (error.getCode()) {
                case 400:
                    log.error("ERROR : " + error.getMessage());
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                case 404:
                    log.error("ERROR : " + error.getMessage());
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                default:
                    log.error("ERROR :" + error.getMessage(), error);
                    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @PutMapping("/fotoPerfil")
    public ResponseEntity<Void> actualizarFotoPerfil (@RequestParam Integer idUsuario, @RequestBody PutUsuarioImagenDto body) {
        try {
            usuariosService.actualizarFotoPerfil(idUsuario, body);
            return new ResponseEntity<Void>(HttpStatus.OK);

        } catch (ApiException error){
            switch (error.getCode()) {
                case 400:
                    log.error("ERROR : " + error.getMessage());
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                case 404:
                    log.error("ERROR : " + error.getMessage());
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                default:
                    log.error("ERROR :" + error.getMessage(), error);
                    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

}
