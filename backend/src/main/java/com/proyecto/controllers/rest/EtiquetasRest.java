package com.proyecto.controllers.rest;

import com.proyecto.dtos.ResponseDto;
import com.proyecto.dtos.TagCreateDto;
import com.proyecto.dtos.TagResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/etiquetas")
public interface EtiquetasRest {

    @GetMapping("")
    ResponseEntity<TagResponseDto> obtenerEtiqueta(@RequestParam(name = "id_etiqueta") Integer idEtiqueta);

    @PostMapping("")
    ResponseEntity<ResponseDto> crearEtiqueta(@Validated @RequestBody TagCreateDto body);

    @DeleteMapping("")
    ResponseEntity<ResponseDto> borrarEtiqueta(@RequestParam(name = "id_etiqueta") Integer idEtiqueta);

    @PutMapping("")
    ResponseEntity<ResponseDto> actualziarEtiqueta(@Validated @RequestParam(name = "id_etiqueta") Integer idEtiqueta, @RequestBody TagCreateDto body);

}
