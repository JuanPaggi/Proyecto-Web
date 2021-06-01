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

/**
 * Capa de controlador para los usuarios.
 * Donde se reciben todas las peticiones Rest.
 */

@RestController
@RequestMapping("/usuarios")
public class UsuariosController {

    public static final Logger log = LoggerFactory.getLogger(UsuariosController.class);

    @Autowired
    UsuariosService usuariosService;

    @PostMapping("/login")
    public ResponseEntity<Void> verificarUsuario(@RequestBody UserLoginDto body, HttpServletRequest request) {
        try {
            usuariosService.verificarUser(body, request);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ApiException error) {
            switch (error.getCode()) {
                case 300:
                    log.error("REDIRECTION : " + error.getMessage());
                    return new ResponseEntity<>(HttpStatus.MULTIPLE_CHOICES);
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
    public ResponseEntity<UserResponseDto> obtenerUsuario(HttpServletRequest request) {
        try {
            UserResponseDto salida = usuariosService.obtenerUsuario(request);
            return new ResponseEntity<>(salida, HttpStatus.OK);
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
    public ResponseEntity<Integer> crearUsuario(@RequestBody UserCreateDto body) {
        try {
            Integer usuario = usuariosService.crearUsuario(body);
            return new ResponseEntity<>(usuario, HttpStatus.OK);
        } catch (ApiException error) {
            switch (error.getCode()) {
                case 409:
                    log.error("ERROR : " + error.getMessage());
                    return new ResponseEntity<>(HttpStatus.CONFLICT);
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
    public ResponseEntity<Void> actualizarUsuario(@RequestBody UserModifyDto body, HttpServletRequest request) {
        try {
            usuariosService.actualizarUsuario(request, body);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ApiException error) {
            switch (error.getCode()) {
                case 401:
                    log.error("ERROR : " + error.getMessage());
                    return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
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
    public ResponseEntity<Void> actualizarFotoPerfil(@RequestBody UserPhotoDto body, HttpServletRequest request) {
        try {
            usuariosService.actualizarFotoPerfil(request, body);
            return new ResponseEntity<>(HttpStatus.OK);
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

    @GetMapping(path = "/verificarMail/{usuario}/{codigo}")
    public ResponseEntity<Boolean> verificarCodigo(@PathVariable("usuario") String usuario, @PathVariable("codigo") String codigo) {
        try {
            return new ResponseEntity<>(usuariosService.verificarCodigoMail(usuario, codigo), HttpStatus.OK);
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

}
