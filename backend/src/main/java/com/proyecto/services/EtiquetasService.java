package com.proyecto.services;

import com.proyecto.dtos.TagResponseDto;
import com.proyecto.dtos.TagCreateDto;
import com.proyecto.models.EtiquetaModels;
import com.proyecto.repository.EtiquetasRepository;
import com.proyecto.exceptions.ApiException;
import com.proyecto.utils.Constantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Capa de servicio para las etiquetas.
 */

@Service
public class EtiquetasService {

    @Autowired
    EtiquetasRepository etiquetasRepository;

    public TagResponseDto obtenerEtiqueta(int idEtiqueta) {
        Optional<EtiquetaModels> etiquetaDB = etiquetasRepository.findById(idEtiqueta);
        if (etiquetaDB.isPresent()) {
            TagResponseDto salida = new TagResponseDto();
            salida.setIdEtiqueta(etiquetaDB.get().getIdEtiqueta());
            salida.setEtiqueta(etiquetaDB.get().getEtiqueta());
            return salida;
        } else {
            throw new ApiException(404, Constantes.ERROR_NO_EXISTE);
        }
    }

    public Integer crearEtiqueta(TagCreateDto entrada) {
        if (entrada.getEtiqueta().length() <= 100) {
            Integer existedb = etiquetasRepository.verificarExisteEtiqueta(entrada.getEtiqueta());
            if (existedb.equals(1)) {
                throw new ApiException(409, "La etiqueta ya existe");
            }
            EtiquetaModels etiqueta = new EtiquetaModels();
            etiqueta.setEtiqueta(entrada.getEtiqueta());
            etiqueta = etiquetasRepository.save(etiqueta);
            return etiqueta.getIdEtiqueta();
        } else {
            throw new ApiException(400, Constantes.ERROR_DATOS_INVALIDOS);
        }
    }

    public void borrarEtiqueta(int idEtiqueta) {
        if (!etiquetasRepository.existsById(idEtiqueta)) {
            throw new ApiException(404, Constantes.ERROR_NO_EXISTE);
        } else {
            etiquetasRepository.deleteById(idEtiqueta);
        }
    }

    public int actualizarEtiqueta(int idEtiqueta, TagCreateDto body) {
        Optional<EtiquetaModels> etiquetaDB = etiquetasRepository.findById(idEtiqueta);
        if (etiquetaDB.isPresent()) {
            EtiquetaModels entrada = etiquetaDB.get();
            entrada.setEtiqueta(body.getEtiqueta());
            etiquetasRepository.save(entrada);
            return entrada.getIdEtiqueta();
        } else {
            throw new ApiException(404, Constantes.ERROR_NO_EXISTE);
        }
    }

}
