package com.proyecto.controllers.rest;

import com.proyecto.dtos.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.NoSuchAlgorithmException;

@RequestMapping("/usuarios")
public interface UsuariosRest {

    @PostMapping("/login")
    ResponseEntity<ResponseDto> verificarUsuario(@Validated @RequestBody UserLoginDto body, HttpServletRequest request);

    @GetMapping("")
    ResponseEntity<UserResponseDto> obtenerUsuario(HttpServletRequest request);

    @PostMapping("")
    ResponseEntity<ResponseDto> crearUsuario(@Validated @RequestBody UserCreateDto body) throws NoSuchAlgorithmException;

    @DeleteMapping("")
    ResponseEntity<ResponseDto> borrarUsuario(Integer idUsuario);

    @PutMapping("")
    ResponseEntity<ResponseDto> actualizarUsuario(@Validated @RequestBody UserModifyDto body, HttpServletRequest request);

    @PutMapping("/fotoPerfil")
    ResponseEntity<ResponseDto> actualizarFotoPerfil(@Validated @RequestBody UserPhotoDto body, HttpServletRequest request) throws NoSuchAlgorithmException;

    @GetMapping(path = "/verificarMail/{usuario}/{codigo}")
    ResponseEntity<Boolean> verificarMail(@PathVariable("usuario") String usuario, @PathVariable("codigo") String codigo);

    @PostMapping(path = "/verificarMailReintento")
    ResponseEntity<ResponseDto> verificarMailReintento(@Validated @RequestBody VerificacionCodigoDto body) throws Exception;

    @PostMapping(path = "/recuperarClave")
    ResponseEntity<ResponseDto> recuperarClave(@Validated @RequestBody UserRestorePasswordDto body) throws Exception;

    @GetMapping(path = "/activarClave/{usuario}/{codigo}")
    ResponseEntity<Boolean> activarClave(@PathVariable("usuario") String usuario, @PathVariable("codigo") String codigo);

}
