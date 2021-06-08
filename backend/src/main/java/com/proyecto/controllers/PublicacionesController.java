package com.proyecto.controllers;

import com.proyecto.controllers.rest.PublicacionesRest;
import com.proyecto.dtos.publication.PublicationResponseDto;
import com.proyecto.dtos.publication.PublicationCreateDto;
import com.proyecto.dtos.ResponseDto;
import com.proyecto.services.PublicacionesService;
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
public class PublicacionesController implements PublicacionesRest {

    @Autowired
    PublicacionesService publicacionesService;

    public ResponseEntity<PublicationResponseDto> obtenerPublicacion(Integer idPublicacion) {
        return new ResponseEntity<>(publicacionesService.obetenerPublicacion(idPublicacion), HttpStatus.OK);
    }

    public ResponseEntity<ResponseDto> crearPublicacion(PublicationCreateDto body, HttpServletRequest request) {
        publicacionesService.crearPublicacion(body, request);
        return new ResponseEntity<>(ResponseDto.getInstanceOk(), HttpStatus.OK);
    }

    public ResponseEntity<ResponseDto> borrarPublicacion(Integer idPublicacion, HttpServletRequest request) {
        publicacionesService.borrarPublicacion(idPublicacion, request);
        return new ResponseEntity<>(ResponseDto.getInstanceOk(), HttpStatus.OK);
    }

    public ResponseEntity<ResponseDto> actualizarPublicacion(Integer idPublicacion, PublicationCreateDto body, HttpServletRequest request) {
        publicacionesService.actualizarPublicacion(idPublicacion, body, request);
        return new ResponseEntity<>(ResponseDto.getInstanceOk(), HttpStatus.OK);
    }

}
