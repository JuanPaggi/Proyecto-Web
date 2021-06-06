package com.proyecto.services;


import com.proyecto.dtos.GalleryResponseDto;
import com.proyecto.dtos.ImageResponseDto;
import com.proyecto.dtos.TagResponseDto;
import com.proyecto.dtos.UserNameResponseDto;
import com.proyecto.exceptions.ApiException;
import com.proyecto.models.EtiquetaModels;
import com.proyecto.models.GaleriaModels;
import com.proyecto.models.ImagenModels;
import com.proyecto.repository.GaleriasRepository;
import com.proyecto.utils.Constantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GaleriasService {

    @Autowired
    GaleriasRepository galeriasRepository;

    public GalleryResponseDto obtenerGaleria(int idGaleria) {
        Optional<GaleriaModels> galeriaDB = galeriasRepository.findById(idGaleria);

        if (galeriaDB.isPresent()) {
            GalleryResponseDto salida = new GalleryResponseDto();
            salida.setIdGaleria(galeriaDB.get().getIdGaleria());
            salida.setDescripcion(galeriaDB.get().getDescripcion());
            salida.setTitulo(galeriaDB.get().getTitulo());
            salida.setFechaCreacion(galeriaDB.get().getFechaCreacion());

            UserNameResponseDto userSalida = new UserNameResponseDto();
            userSalida.setIdUsuario(galeriaDB.get().getUsuario().getIdUsuario());
            userSalida.setUser(galeriaDB.get().getUsuario().getUser());
            salida.setUsuario(userSalida);

            List<TagResponseDto> etiquetasSalida = new ArrayList<>();

            for (EtiquetaModels it:  galeriaDB.get().getEtiquetas() ) {

                TagResponseDto tag = new TagResponseDto();
                tag.setIdEtiqueta(it.getIdEtiqueta());
                tag.setEtiqueta(it.getEtiqueta());
                etiquetasSalida.add(tag);
            }
            salida.setEtiquetas(etiquetasSalida);

            List<ImageResponseDto> imagenSalida = new ArrayList<>();

            for (ImagenModels it: galeriaDB.get().getImagenes()) {

                ImageResponseDto img = new ImageResponseDto();
                img.setImagen(it.getImagen());
                img.setFechaSubida(it.getFechaSubida());

                UserNameResponseDto usuarioSalida = new UserNameResponseDto();
                usuarioSalida.setIdUsuario(it.getUsuario().getIdUsuario());
                usuarioSalida.setUser(it.getUsuario().getUser());
                img.setUsuario(usuarioSalida);
                imagenSalida.add(img);
            }

            salida.setImagenes(imagenSalida);
            return salida;
        } else {
            throw new ApiException(404, Constantes.ERROR_NO_EXISTE);
        }
    }

    
}
