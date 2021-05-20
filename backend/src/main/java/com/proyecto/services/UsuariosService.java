package com.proyecto.services;

import com.proyecto.dtos.GetUsuarioDto;
import com.proyecto.models.UsuarioModels;
import com.proyecto.repository.PublicacionesRepository;
import com.proyecto.repository.UsuariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuariosService {

    @Autowired
    UsuariosRepository usuariosRepository;

    @Autowired
    PublicacionesRepository publicacionesRepository;

    public GetUsuarioDto obtenerUsuario (int idUsuario){

        try{
            //ahora creo la caja donde los datos viajan de la db al backend
            Optional<UsuarioModels> usuario = usuariosRepository.findById(idUsuario);

            /* pregunto y verifico si el molde de usuario esta presente, le cargo los archivos del getDto
               y los envio al backend */
            if (usuario.isPresent()){
                GetUsuarioDto salida = new GetUsuarioDto();
                salida.setIdUsuario(salida.getIdUsuario());
                salida.setUser(salida.getUser());
                salida.setMail(salida.getMail());
                salida.setNombre(salida.getNombre());
                salida.setApellido(salida.getApellido());
                salida.setFechaRegistro(salida.getFechaRegistro());
                salida.setFechaNacimiento(salida.getFechaNacimiento());
                salida.setAdmin(salida.getAdmin());

            }

        }
    }
}
