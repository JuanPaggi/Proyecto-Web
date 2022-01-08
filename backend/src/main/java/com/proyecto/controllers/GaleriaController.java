package com.proyecto.controllers;

import com.proyecto.controllers.rest.GaleriaRest;
import com.proyecto.dtos.ResponseDto;
import com.proyecto.dtos.gallery.GalleryCreateDto;
import com.proyecto.dtos.gallery.GalleryResponseDto;
import com.proyecto.services.GaleriasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@RestController
@RequestMapping("/galerias")
public class GaleriaController implements GaleriaRest {

    @Autowired
    GaleriasService galeriasService;

    public ResponseEntity<GalleryResponseDto> obtenerGaleria(Integer idGaleria) {
        return new ResponseEntity<>(galeriasService.obtenerGaleria(idGaleria), HttpStatus.OK);
    }

    public ResponseEntity<List<GalleryResponseDto>> getAll() {
        return new ResponseEntity<>(galeriasService.getAll(), HttpStatus.OK);
    }

    public ResponseEntity<ResponseDto> crearGaleria(GalleryCreateDto body, HttpServletRequest request) throws NoSuchAlgorithmException {
        galeriasService.crearGaleria(body, request);
        return new ResponseEntity<>(ResponseDto.getInstanceOk(), HttpStatus.OK);
    }

    public ResponseEntity<ResponseDto> borrarGaleria(Integer idGaleria, HttpServletRequest request) {
        galeriasService.borrarGaleria(idGaleria, request);
        return new ResponseEntity<>(ResponseDto.getInstanceOk(), HttpStatus.OK);
    }

}
