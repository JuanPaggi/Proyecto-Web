package com.proyecto.controllers;

import com.proyecto.dtos.*;
import com.proyecto.services.UsuariosService;
import com.proyecto.utils.ApiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/usuarios")
public class UsuariosController {

    public static final Logger log = LoggerFactory.getLogger(UsuariosController.class);

    @Autowired
    UsuariosService usuariosService;

    @PostMapping("")
    public ResponseEntity<Void> verificarUsuario(@RequestBody LoginUserDto body, HttpServletRequest request) {

        try {
            if (usuariosService.verificarUser(body)) {
                request.getSession(true).setAttribute("user", body.getUser());
            }
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ApiException error) {
            switch (error.getCode()) {
                case 401:
                    log.error("ERROR : " + error.getMessage());
                    return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
                default:
                    log.error("ERROR : " + error.getMessage(), error);
                    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @GetMapping("")
    public ResponseEntity<GetUsuarioDto> obtenerUsuario(HttpServletRequest request) {

        try {
            if (request.getSession(false) != null) {
                String usuario = (String) request.getSession(false).getAttribute("user");
                GetUsuarioDto salida = usuariosService.obtenerUsuario(usuario);
                return new ResponseEntity<>(salida, HttpStatus.OK);
            } else {
                throw new ApiException(401, "No autorizado.");
            }
        } catch (ApiException error) {
            switch (error.getCode()) {
                case 401:
                    log.error("ERROR : " + error.getMessage());
                    return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
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

        } catch (ApiException error) {
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
    public ResponseEntity<Void> actualizarFotoPerfil(@RequestParam Integer idUsuario, @RequestBody PutUsuarioImagenDto body) {
        try {
            usuariosService.actualizarFotoPerfil(idUsuario, body);
            return new ResponseEntity<Void>(HttpStatus.OK);

        } catch (ApiException error) {
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
