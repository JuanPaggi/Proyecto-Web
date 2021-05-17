package com.proyecto.services;

import com.proyecto.dtos.EtiquetaDto;
import com.proyecto.dtos.GetEtiquetaDto;
import com.proyecto.dtos.GetPublicacionDto;
import com.proyecto.dtos.PostPublicacionDto;
import com.proyecto.models.EtiquetaModels;
import com.proyecto.models.PublicacionModels;
import com.proyecto.repository.EtiquetasRepository;
import com.proyecto.repository.PublicacionesRepository;
import com.proyecto.utils.ApiException;
import com.proyecto.utils.Constantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PublicacionesService {

    @Autowired
    PublicacionesRepository publicacionesRepository;

    @Autowired
    EtiquetasRepository etiquetasRepository;

    public GetPublicacionDto obetenerPublicacion(int idPublicacion) {

        try {

            Optional<PublicacionModels> publicacion = publicacionesRepository.obtenerPublicacion(idPublicacion);

            if (publicacion.isPresent()) {
                GetPublicacionDto salida = new GetPublicacionDto();
                salida.setIdPublicacion(publicacion.get().getIdPublicacion());
                salida.setDescripcion(publicacion.get().getDescripcion());
                salida.setFechaCreacion(publicacion.get().getFechaCreacion());
                salida.setTitulo(publicacion.get().getTitulo());

                List<GetEtiquetaDto> etiquetas = new ArrayList<>();
                for (EtiquetaModels it: publicacion.get().getEtiquetas()) {
                    GetEtiquetaDto etiquetaDto = new GetEtiquetaDto();
                    etiquetaDto.setIdEtiqueta(it.getIdEtiqueta());
                    etiquetaDto.setEtiqueta(it.getEtiqueta());
                    etiquetas.add(etiquetaDto);
                }

                salida.setEtiquetas(etiquetas);

                return salida;
            } else {
                throw new ApiException(404, "La publicacion no existe.");
            }

        } catch (ApiException error) {
            throw error;
        } catch (Exception error) {
            throw new ApiException(500, Constantes.ERROR_GENERAL);
        }
    }


    public Integer crearPublicacion(PostPublicacionDto entrada) {

        try {

            if (entrada.getDescripcion().length() <= 10000) {

                PublicacionModels publicacion = new PublicacionModels();
                publicacion.setDescripcion(entrada.getDescripcion());
                publicacion.setFechaCreacion(new Date()); //crea la fecha en el momento
                publicacion.setTitulo(entrada.getTitulo());

                List<EtiquetaModels> etiquetas = etiquetasRepository.findAllById(entrada.getEtiquetas());

                if(etiquetas.size() != entrada.getEtiquetas().size()){
                    throw new ApiException(404, "Alguna de las etiquetas recibidas no exite");
                }

                publicacion.setEtiquetas(etiquetas);

                publicacion = publicacionesRepository.save(publicacion);

                return publicacion.getIdPublicacion();
            } else {
                throw new ApiException(400, "los datos enviados no son validos");
            }
        } catch (ApiException error) {
            throw error;
        } catch (Exception error) {
            throw new ApiException(500, Constantes.ERROR_GENERAL);
        }
    }

    public void borrarPublicacion(int idPublicacion) {

        try {
            if (!publicacionesRepository.existsById(idPublicacion)){
                throw new ApiException(404, "La publicacion no existe.");
            } else {
                publicacionesRepository.deleteById(idPublicacion);
            }
        } catch (ApiException error) {
            throw error;
        } catch (Exception error) {
            throw new ApiException(500, Constantes.ERROR_GENERAL);
        }

    }

    public int actualizarPublicacion(int idPublicacion, PostPublicacionDto body){
        try{
            Optional<PublicacionModels> publicacionDB = publicacionesRepository.findById(idPublicacion);

            if(publicacionDB.isPresent()){
                PublicacionModels entrada = publicacionDB.get();

                if (entrada.getTitulo() != null) {
                    entrada.setTitulo(body.getTitulo());
                }

                if (entrada.getDescripcion() != null) {
                    entrada.setDescripcion(body.getDescripcion());
                }
                publicacionesRepository.save(entrada);
                return entrada.getIdPublicacion();

            } else {
                throw new ApiException(404, "La Publicacion no existe.");
            }
        } catch (ApiException error) {
            throw error;
        } catch (Exception error){
            throw new ApiException(500, Constantes.ERROR_GENERAL);
        }
    }



}
