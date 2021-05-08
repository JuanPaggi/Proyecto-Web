package com.proyecto.services;

import com.proyecto.dtos.GetEtiquetaDto;
import com.proyecto.dtos.PostEtiquetaDto;
import com.proyecto.models.EtiquetaModels;
import com.proyecto.repository.EtiquetasRepository;
import com.proyecto.utils.ApiException;
import com.proyecto.utils.Constantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 *  Capa de servicio.
 *  Aca tenemos toda la logica de negocio.
 */

@Service
public class EtiquetasService {

    @Autowired
    EtiquetasRepository etiquetasRepository;

    /*
        Obtenemos la etiqueta de la base, armamos el DTO y la retornamos a la capa de controlador.
     */

    public GetEtiquetaDto obtenerEtiqueta(int idEtiqueta) {

        try {

            Optional<EtiquetaModels> etiqueta = etiquetasRepository.obtenerEtiqueta(idEtiqueta);

            if (etiqueta.isPresent()) {
                GetEtiquetaDto salida = new GetEtiquetaDto();
                salida.setIdEtiqueta(etiqueta.get().getIdEtiqueta());
                salida.setEtiqueta(etiqueta.get().getEtiqueta());
                return salida;
            } else {
                throw new ApiException(404, "La etiqueta no existe.");
            }

        } catch (ApiException error) {
            throw error;
        } catch (Exception error) {
            throw new ApiException(500, Constantes.ERROR_GENERAL);
        }

    }

    /*
        Recibimos la informacion de la etiqueta, comprobamos si longitud
        y la guardamos en la base con el metodo save() de JPA.
     */

    public void crearEtiqueta(PostEtiquetaDto entrada) {

        try {

            if (entrada.getEtiqueta().length() <= 100) {
                EtiquetaModels etiqueta = new EtiquetaModels();
                etiqueta.setEtiqueta(entrada.getEtiqueta());
                etiquetasRepository.save(etiqueta);
            } else {
                throw new ApiException(400, "Los datos enviados no son validos");
            }

        } catch (ApiException error) {
            throw error;
        } catch (Exception error) {
            throw new ApiException(500, Constantes.ERROR_GENERAL);
        }

    }

}
