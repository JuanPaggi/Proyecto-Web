package com.proyecto.controllers;

import com.proyecto.controllers.rest.UsuariosRest;
import com.proyecto.dtos.*;
import com.proyecto.dtos.user.*;
import com.proyecto.services.UsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.NoSuchAlgorithmException;

/**
 * Capa de controlador para los usuarios.
 * Donde se reciben todas las peticiones Rest.
 */

@RestController
public class UsuariosController implements UsuariosRest {

    @Autowired
    UsuariosService usuariosService;

    public ResponseEntity<ResponseDto> verificarUsuario(UserLoginDto body, HttpServletRequest request) {
        usuariosService.verificarUser(body, request);
        return new ResponseEntity<>(ResponseDto.getInstanceOk(), HttpStatus.OK);
    }

    public ResponseEntity<UserResponseDto> obtenerUsuario(HttpServletRequest request) {
        return new ResponseEntity<>(usuariosService.obtenerUsuario(request), HttpStatus.OK);
    }

    public ResponseEntity<ResponseDto> crearUsuario(UserCreateDto body) throws NoSuchAlgorithmException {
        usuariosService.crearUsuario(body);
        return new ResponseEntity<>(ResponseDto.getInstanceOk(), HttpStatus.OK);
    }

    public ResponseEntity<ResponseDto> borrarUsuario(Integer idUsuario) {
        usuariosService.borrarUsuario(idUsuario);
        return new ResponseEntity<>(ResponseDto.getInstanceOk(), HttpStatus.OK);
    }

    public ResponseEntity<ResponseDto> actualizarUsuario(UserModifyDto body, HttpServletRequest request) {
        usuariosService.actualizarUsuario(request, body);
        return new ResponseEntity<>(ResponseDto.getInstanceOk(), HttpStatus.OK);
    }

    public ResponseEntity<ResponseDto> actualizarFotoPerfil(UserPhotoDto body, HttpServletRequest request) throws NoSuchAlgorithmException {
        usuariosService.actualizarFotoPerfil(request, body);
        return new ResponseEntity<>(ResponseDto.getInstanceOk(), HttpStatus.OK);
    }

    public ResponseEntity<Boolean> verificarMail(String usuario, String codigo) {
        return new ResponseEntity<>(usuariosService.verificarCodigoMail(usuario, codigo), HttpStatus.OK);
    }

    public ResponseEntity<ResponseDto> verificarMailReintento(VerificacionCodigoDto body) throws Exception {
        usuariosService.verificarCodigoMailReintento(body);
        return new ResponseEntity<>(ResponseDto.getInstanceOk(), HttpStatus.OK);
    }

    public ResponseEntity<ResponseDto> recuperarClave(UserRestorePasswordDto body) throws Exception {
        usuariosService.restaurarClave(body);
        return new ResponseEntity<>(ResponseDto.getInstanceOk(), HttpStatus.OK);
    }

    public ResponseEntity<Boolean> activarClave(String usuario, String codigo) {
        return new ResponseEntity<>(usuariosService.activarClave(usuario, codigo), HttpStatus.OK);
    }

}
