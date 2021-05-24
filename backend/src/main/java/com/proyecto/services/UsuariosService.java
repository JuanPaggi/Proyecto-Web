package com.proyecto.services;

import com.proyecto.dtos.GetUsuarioDto;
import com.proyecto.dtos.PutUsuarioDto;
import com.proyecto.dtos.PutUsuarioImagenDto;
import com.proyecto.dtos.UsuarioDto;
import com.proyecto.models.ImagenModels;
import com.proyecto.models.UsuarioModels;
import com.proyecto.repository.UsuariosRepository;
import com.proyecto.utils.ApiException;
import com.proyecto.utils.Constantes;
import com.proyecto.utils.Sha1Hasher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.Optional;
import java.util.Random;

@Service
public class UsuariosService {

    @Autowired
    UsuariosRepository usuariosRepository;

    @Autowired
    ImagenesService imagenesService;

    public GetUsuarioDto obtenerUsuario(int idUsuario) {

        try {
            //ahora creo la caja donde los datos viajan de la db al backend

            Optional<UsuarioModels> usuario = usuariosRepository.findById(idUsuario);

            /* pregunto y verifico si el molde de usuario esta presente, le cargo los archivos al getDto
               y los envio al frontend */

            if (usuario.isPresent()) {
                GetUsuarioDto salida = new GetUsuarioDto();
                salida.setIdUsuario(usuario.get().getIdUsuario());
                salida.setUser(usuario.get().getUser());
                salida.setMail(usuario.get().getMail());
                salida.setNombre(usuario.get().getNombre());
                salida.setApellido(usuario.get().getApellido());
                salida.setFechaRegistro(usuario.get().getFechaRegistro());
                salida.setFechaNacimiento(usuario.get().getFechaNacimiento());
                salida.setAdmin(usuario.get().getAdmin());
                salida.setMailVerificado(usuario.get().getMailVerificado());
                salida.setCodigoVerificacion(usuario.get().getCodigoVerificacion());

                if (usuario.get().getImagenPerfil() != null) {
                    salida.setIdImagen(usuario.get().getImagenPerfil().getIdImagen());
                }

                return salida;
            } else {
                throw new ApiException(404, "El usuario no existe.");
            }
        } catch (ApiException error) {
            throw error;
        } catch (Exception error) {
            throw new ApiException(500, Constantes.ERROR_GENERAL);
        }
    }

    //
    public Integer crearUsuario(UsuarioDto entrada) {
        try {
            if (entrada.getUser().length() >= 8 && entrada.getUser().length() <= 30
                    && entrada.getClave().length() >= 8 && entrada.getClave().length() <= 50
                    && entrada.getMail().length() <= 100 && entrada.getMail().contains("@") && !entrada.getMail().contains("+")) {

                UsuarioModels usuario = new UsuarioModels();
                usuario.setUser(entrada.getUser());
                usuario.setMail(entrada.getMail());
                usuario.setClave(entrada.getClave());
                usuario.setNombre(entrada.getNombre());
                usuario.setApellido(entrada.getApellido());
                usuario.setFechaNacimiento(entrada.getFechaNacimiento());
                usuario.setAdmin(entrada.getAdmin());
                usuario.setFechaRegistro(new Date());
                usuario.setMailVerificado(false);

                Random rand = new Random();
                Integer n = rand.nextInt(999999);
                usuario.setCodigoVerificacion(n.toString());

                if (entrada.getImagen() != null) {

                    byte[] hash = Sha1Hasher.hashBytes(entrada.getImagen());
                    Optional<ImagenModels> imagen = imagenesService.obtenerImagenPorHash(hash);
                    if (imagen.isPresent()) {
                        usuario.setImagenPerfil(imagen.get());
                    } else {
                        usuario.setImagenPerfil(imagenesService.cargarImagen(entrada.getImagen()));
                    }
                }

                usuario = usuariosRepository.save(usuario);
                return usuario.getIdUsuario();
            } else {
                throw new ApiException(400, "Los datos enviados no son validos");
            }
        } catch (ApiException error) {
            throw error;
        } catch (Exception error) {
            throw new ApiException(500, Constantes.ERROR_GENERAL);
        }
    }

    public void borrarUsuario(int idUsuario) {

        try {
            if (!usuariosRepository.existsById(idUsuario)) {
                throw new ApiException(404, "El usuario no existe");
            } else {
                usuariosRepository.deleteById(idUsuario);
            }
        } catch (ApiException error) {
            throw error;
        } catch (Exception error) {
            throw new ApiException(500, Constantes.ERROR_GENERAL);
        }
    }

    public int actualizarUsuario(int idUsuario, PutUsuarioDto entrada) {
        try {
            Optional<UsuarioModels> userDB = usuariosRepository.findById(idUsuario);

            if (entrada.getClave() == null) {
                throw new ApiException(400, "Clave no enviada.");
            }

            if (userDB.isPresent()) {
                UsuarioModels user = userDB.get();

                if (entrada.getUser() != null) {

                    if (user.getClave().equals(entrada.getClave())
                            && entrada.getUser().length() >= 8 && entrada.getUser().length() <= 30) {

                        user.setUser(entrada.getUser());
                    } else {
                        throw new ApiException(400, "Los datos enviados no son validos.");
                    }
                }

                if (entrada.getMail() != null) {

                    if (user.getClave().equals(entrada.getClave()) && entrada.getMail().length() <= 100) {
                        user.setMail(entrada.getMail());
                    } else {
                        throw new ApiException(400, "Los datos enviados no son validos.");
                    }
                }

                if (entrada.getNuevaClave() != null) {

                    if (user.getClave().equals(entrada.getClave()) && entrada.getNuevaClave().length() >= 8 &&
                            entrada.getNuevaClave().length() <= 50) {

                        user.setClave(entrada.getNuevaClave());
                    } else {
                        throw new ApiException(400, "Los datos enviados no son validos.");
                    }
                }

                usuariosRepository.save(user);
                return user.getIdUsuario();

            } else {
                throw new ApiException(404, "El usuario no existe.");
            }
        } catch (ApiException error) {
            throw error;
        } catch (Exception error) {
            throw new ApiException(500, Constantes.ERROR_GENERAL);
        }
    }

    public void actualizarFotoPerfil(Integer idUsuario, PutUsuarioImagenDto body) {
        try {
            Optional<UsuarioModels> userDB = usuariosRepository.findById(idUsuario);

            if (userDB.isPresent()) {
                byte[] hash = Sha1Hasher.hashBytes(body.getImagen());
                Optional<ImagenModels> imagen = imagenesService.obtenerImagenPorHash(hash);

                if (imagen.isPresent()) {
                    userDB.get().setImagenPerfil(imagen.get());
                } else {
                    userDB.get().setImagenPerfil(imagenesService.cargarImagen(body.getImagen()));
                }

                usuariosRepository.save(userDB.get());
            } else {
                throw new ApiException(404, "El usuario no existe");
            }
        } catch (ApiException error) {
            throw error;
        } catch (Exception error) {
            throw new ApiException(500, Constantes.ERROR_GENERAL);
        }
    }
}
