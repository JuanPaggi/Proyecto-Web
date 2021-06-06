package com.proyecto.controllers;

import com.proyecto.controllers.rest.GaleriaRest;
import com.proyecto.dtos.GalleryResponseDto;
import com.proyecto.services.GaleriasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/galerias")
public class GaleriaController implements GaleriaRest {

    @Autowired
    GaleriasService galeriasService;


    public ResponseEntity<GalleryResponseDto> obtenerGaleria(Integer idGaleria) {
        return null;
    }
}
