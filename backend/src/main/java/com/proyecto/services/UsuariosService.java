package com.proyecto.services;

import com.proyecto.dtos.*;
import com.proyecto.models.ImagenModels;
import com.proyecto.models.UsuarioModels;
import com.proyecto.repository.UsuariosRepository;
import com.proyecto.utils.ApiException;
import com.proyecto.utils.Constantes;
import com.proyecto.utils.Sha1Hasher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Optional;
import java.util.Random;

@Service
public class UsuariosService {

    @Autowired
    UsuariosRepository usuariosRepository;

    @Autowired
    ImagenesService imagenesService;

    public void verificarUser(LoginUserDto entrada, HttpServletRequest request) {
        try {

            Optional<UsuarioModels> user = usuariosRepository.loguearUsuario(entrada.getUser(), entrada.getClave());

            if (user.isPresent()) {
                request.getSession(true).setAttribute("user", entrada.getUser());
            } else {
                throw new ApiException(401, "Credenciales invalidas.");
            }

        } catch (ApiException error) {
            throw error;
        } catch (Exception error) {
            throw new ApiException(500, Constantes.ERROR_GENERAL);
        }
    }

    public GetUsuarioDto obtenerUsuario(HttpServletRequest request) {

        try {
            String userInput = "";

            if (request.getSession(false) != null) {
                userInput = (String) request.getSession(false).getAttribute("user");
            } else {
                throw new ApiException(401, "No autorizado.");
            }
            //ahora creo la caja donde los datos viajan de la db al backend

            Optional<UsuarioModels> usuario = usuariosRepository.obtenerUsuario(userInput);

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

    public Integer crearUsuario(UsuarioDto entrada) {
        try {
            if (entrada.getUser().length() >= 8 && entrada.getUser().length() <= 30
                    && entrada.getClave().length() >= 8 && entrada.getClave().length() <= 50
                    && entrada.getMail().length() <= 100 && entrada.getMail().contains("@") && !entrada.getMail().contains("+")) {

                Optional<UsuarioModels> usuarioExistente = usuariosRepository.comprobarUsuarioRepetido(entrada.getUser(), entrada.getMail());
                if (usuarioExistente.isPresent()) {
                    throw new ApiException(409, "El usuario ya existe");
                }

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

    public int actualizarUsuario(HttpServletRequest request, PutUsuarioDto entrada) {
        try {
            String userInput = "";

            if (request.getSession(false) != null) {
                userInput = (String) request.getSession(false).getAttribute("user");

            } else {
                throw new ApiException(401, "Usuario no autorizado.");
            }

            Optional<UsuarioModels> userDB = usuariosRepository.obtenerUsuario(userInput);

            if (entrada.getClave() == null) {
                throw new ApiException(400, "Clave no enviada.");
            }

            if (userDB.isPresent()) {
                UsuarioModels user = userDB.get();

                if (entrada.getUser() != null) {

                    if (user.getClave().equals(entrada.getClave())
                            && entrada.getUser().length() >= 8 && entrada.getUser().length() <= 30) {

                        request.getSession(true).setAttribute("user", entrada.getUser());
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

    public UsuarioModels actualizarFotoPerfil(HttpServletRequest request, PutUsuarioImagenDto body) {
        try {
            String userInput = "";

            if (request.getSession(false) != null) {  //Si la session es distinta de null , le concedo la autorizacion
                userInput = (String) request.getSession(false).getAttribute("user");
            } else {
                throw new ApiException(401, "Usuario no autorizado.");
            }

            Optional<UsuarioModels> userDB = usuariosRepository.obtenerUsuario(userInput);

            if (userDB.isPresent()) {
                byte[] hash = Sha1Hasher.hashBytes(body.getImagen());
                Optional<ImagenModels> imagen = imagenesService.obtenerImagenPorHash(hash);

                if (imagen.isPresent()) {
                    userDB.get().setImagenPerfil(imagen.get());
                } else {
                    userDB.get().setImagenPerfil(imagenesService.cargarImagen(body.getImagen()));
                }

                usuariosRepository.save(userDB.get());
                return userDB.get();
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
