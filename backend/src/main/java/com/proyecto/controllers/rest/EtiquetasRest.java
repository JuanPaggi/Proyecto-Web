package com.proyecto.controllers.rest;

import com.proyecto.dtos.ResponseDto;
import com.proyecto.dtos.tag.TagCreateDto;
import com.proyecto.dtos.tag.TagResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequestMapping("/etiquetas")
public interface EtiquetasRest {

    @GetMapping("/{idEtiqueta}")
    ResponseEntity<TagResponseDto> obtenerEtiqueta(@PathVariable("idEtiqueta") Integer idEtiqueta);

    @GetMapping("/all")
    ResponseEntity<List<TagResponseDto>> getAll(HttpServletRequest request);

    @PostMapping("")
    ResponseEntity<ResponseDto> crearEtiqueta(@Validated @RequestBody TagCreateDto body, HttpServletRequest request);

    @DeleteMapping("")
    ResponseEntity<ResponseDto> borrarEtiqueta(@RequestParam(name = "id_etiqueta") Integer idEtiqueta, HttpServletRequest request);

    @PutMapping("")
    ResponseEntity<ResponseDto> actualziarEtiqueta(@Validated @RequestParam(name = "id_etiqueta") Integer idEtiqueta, @RequestBody TagCreateDto body, HttpServletRequest request);

}
