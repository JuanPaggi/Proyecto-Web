package com.proyecto.services;


import com.proyecto.dtos.*;
import com.proyecto.dtos.gallery.GalleryCreateDto;
import com.proyecto.dtos.gallery.GalleryResponseDto;
import com.proyecto.dtos.tag.TagResponseDto;
import com.proyecto.dtos.user.UserNameResponseDto;
import com.proyecto.exceptions.ApiException;
import com.proyecto.models.EtiquetaModels;
import com.proyecto.models.GaleriaModels;
import com.proyecto.models.ImagenModels;
import com.proyecto.models.UsuarioModels;
import com.proyecto.repository.EtiquetasRepository;
import com.proyecto.repository.GaleriasRepository;
import com.proyecto.repository.UsuariosRepository;
import com.proyecto.utils.Constantes;
import com.proyecto.utils.Sha1Hasher;
import com.proyecto.utils.Validaciones;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class GaleriasService {

    @Autowired
    GaleriasRepository galeriasRepository;

    @Autowired
    EtiquetasRepository etiquetasRepository;

    @Autowired
    ImagenesService imagenesService;

    @Autowired
    UsuariosRepository usuariosRepository;

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
            for (EtiquetaModels it : galeriaDB.get().getEtiquetas()) {

                TagResponseDto tag = new TagResponseDto();
                tag.setIdEtiqueta(it.getIdEtiqueta());
                tag.setEtiqueta(it.getEtiqueta());
                etiquetasSalida.add(tag);
            }
            salida.setEtiquetas(etiquetasSalida);
            List<ImageResponseDto> imagenSalida = new ArrayList<>();
            for (ImagenModels it : galeriaDB.get().getImagenes()) {
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

    public List<GalleryResponseDto> getAll(HttpServletRequest request) {
        Validaciones.obtenerUserLogin(request);
        List<GaleriaModels> gallerys = galeriasRepository.getAll();
        List<GalleryResponseDto> result = new ArrayList<>();
        gallerys.forEach(it ->{
            GalleryResponseDto gallery = new GalleryResponseDto();
            gallery.setIdGaleria(it.getIdGaleria());
            gallery.setDescripcion(it.getDescripcion());
            gallery.setTitulo(it.getTitulo());
            gallery.setFechaCreacion(it.getFechaCreacion());
            result.add(gallery);
        });
        return result;
    }

    public void crearGaleria(GalleryCreateDto body, HttpServletRequest request) throws NoSuchAlgorithmException {
        String userInput = Validaciones.obtenerUserLogin(request);
        Optional<UsuarioModels> user = usuariosRepository.obtenerUsuario(userInput);
        if (!user.isPresent()) {
            throw new ApiException(404, Constantes.ERROR_NO_EXISTE);
        }
        if (!user.get().getAdmin()) {
            throw new ApiException(401, Constantes.ERROR_NO_AUTORIZADO);
        }
        GaleriaModels galeria = new GaleriaModels();
        galeria.setTitulo(body.getTitulo());
        galeria.setDescripcion(body.getDescripcion());
        galeria.setFechaCreacion(new Date());
        galeria.setUsuario(user.get());
        if (body.getEtiquetas() != null) {
            List<EtiquetaModels> etiquetas = etiquetasRepository.findAllById(body.getEtiquetas());
            galeria.setEtiquetas(etiquetas);
        }
        if (body.getImagenes() != null) {
            List<ImagenModels> imagenes = new ArrayList<>();
            for (byte[] it : body.getImagenes()) {
                byte[] hash = Sha1Hasher.hashBytes(it);
                Optional<ImagenModels> imagen = imagenesService.obtenerImagenPorHash(hash);
                if (imagen.isPresent()) {
                    imagenes.add(imagen.get());
                } else {
                    imagenes.add(imagenesService.cargarImagen(it, user.get()));
                }
            }
            galeria.setImagenes(imagenes);
        }
        galeriasRepository.save(galeria);
    }

    public void borrarGaleria(Integer idGaleria, HttpServletRequest request) {
        String userInput = Validaciones.obtenerUserLogin(request);
        Optional<UsuarioModels> user = usuariosRepository.obtenerUsuario(userInput);
        if (!user.isPresent()) {
            throw new ApiException(404, "El usuario no existe");
        }
        if (!user.get().getAdmin()) {
            throw new ApiException(401, Constantes.ERROR_NO_AUTORIZADO);
        }
        if (!galeriasRepository.existsById(idGaleria)) {
            throw new ApiException(404, Constantes.ERROR_NO_EXISTE);
        } else {
            galeriasRepository.deleteById(idGaleria);
        }
    }

}
