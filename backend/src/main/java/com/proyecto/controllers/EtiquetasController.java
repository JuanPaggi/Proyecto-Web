package com.proyecto.controllers;

import com.proyecto.controllers.rest.EtiquetasRest;
import com.proyecto.dtos.ResponseDto;
import com.proyecto.dtos.tag.TagResponseDto;
import com.proyecto.dtos.tag.TagCreateDto;
import com.proyecto.services.EtiquetasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Capa de controlador Para las etiquetas.
 * Donde se reciben todas las peticiones Rest.
 */

@RestController
public class EtiquetasController implements EtiquetasRest {

    @Autowired
    EtiquetasService etiquetasService;

    public ResponseEntity<TagResponseDto> obtenerEtiqueta(Integer idEtiqueta) {
        return new ResponseEntity<>(etiquetasService.obtenerEtiqueta(idEtiqueta), HttpStatus.OK);
    }

    public ResponseEntity<ResponseDto> crearEtiqueta(TagCreateDto body) {
        etiquetasService.crearEtiqueta(body);
        return new ResponseEntity<>(ResponseDto.getInstanceOk(), HttpStatus.OK);
    }

    public ResponseEntity<ResponseDto> borrarEtiqueta(Integer idEtiqueta) {
        etiquetasService.borrarEtiqueta(idEtiqueta);
        return new ResponseEntity<>(ResponseDto.getInstanceOk(), HttpStatus.OK);
    }

    public ResponseEntity<ResponseDto> actualziarEtiqueta(Integer idEtiqueta, TagCreateDto body) {
        etiquetasService.actualizarEtiqueta(idEtiqueta, body);
        return new ResponseEntity<>(ResponseDto.getInstanceOk(), HttpStatus.OK);
    }

}
