package com.proyecto.services;

import com.proyecto.dtos.tag.TagResponseDto;
import com.proyecto.dtos.tag.TagCreateDto;
import com.proyecto.models.EtiquetaModels;
import com.proyecto.models.UsuarioModels;
import com.proyecto.repository.EtiquetasRepository;
import com.proyecto.exceptions.ApiException;
import com.proyecto.repository.UsuariosRepository;
import com.proyecto.utils.Constantes;
import com.proyecto.utils.Validaciones;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 * Capa de servicio para las etiquetas.
 */

@Service
public class EtiquetasService {

    @Autowired
    EtiquetasRepository etiquetasRepository;

    @Autowired
    UsuariosRepository usuariosRepository;

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

    public void crearEtiqueta(TagCreateDto entrada, HttpServletRequest request) {
        String userInput = Validaciones.obtenerUserLogin(request);
        Optional<UsuarioModels> user = usuariosRepository.obtenerUsuario(userInput);
        if (user.isPresent()) {
            if (entrada.getEtiqueta().length() <= 100) {
                Integer existedb = etiquetasRepository.verificarExisteEtiqueta(entrada.getEtiqueta());
                if (existedb.equals(1)) {
                    throw new ApiException(409, "La etiqueta ya existe");
                }
                EtiquetaModels etiqueta = new EtiquetaModels();
                etiqueta.setEtiqueta(entrada.getEtiqueta());
                etiquetasRepository.save(etiqueta);
            } else {
                throw new ApiException(400, Constantes.ERROR_DATOS_INVALIDOS);
            }
        } else {
            throw new ApiException(401, Constantes.ERROR_NO_AUTORIZADO);
        }
    }

    public void borrarEtiqueta(int idEtiqueta, HttpServletRequest request) {
        String userInput = Validaciones.obtenerUserLogin(request);
        Optional<UsuarioModels> user = usuariosRepository.obtenerUsuario(userInput);
        if (user.isPresent() && user.get().getAdmin()) {
            if (!etiquetasRepository.existsById(idEtiqueta)) {
                throw new ApiException(404, Constantes.ERROR_NO_EXISTE);
            } else {
                etiquetasRepository.deleteById(idEtiqueta);
            }
        } else {
            throw new ApiException(401, Constantes.ERROR_NO_AUTORIZADO);
        }
    }

    public void actualizarEtiqueta(int idEtiqueta, TagCreateDto body, HttpServletRequest request) {
        String userInput = Validaciones.obtenerUserLogin(request);
        Optional<UsuarioModels> user = usuariosRepository.obtenerUsuario(userInput);
        if (user.isPresent() && user.get().getAdmin()) {
            Optional<EtiquetaModels> etiquetaDB = etiquetasRepository.findById(idEtiqueta);
            if (etiquetaDB.isPresent()) {
                EtiquetaModels entrada = etiquetaDB.get();
                entrada.setEtiqueta(body.getEtiqueta());
                etiquetasRepository.save(entrada);
            } else {
                throw new ApiException(404, Constantes.ERROR_NO_EXISTE);
            }
        } else {
            throw new ApiException(401, Constantes.ERROR_NO_AUTORIZADO);
        }
    }

}
