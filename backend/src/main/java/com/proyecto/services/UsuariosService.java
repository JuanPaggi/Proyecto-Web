package com.proyecto.services;

import com.proyecto.dtos.*;
import com.proyecto.models.ImagenModels;
import com.proyecto.models.UsuarioModels;
import com.proyecto.repository.UsuariosRepository;
import com.proyecto.exceptions.ApiException;
import com.proyecto.utils.Constantes;
import com.proyecto.utils.SendMail;
import com.proyecto.utils.Sha1Hasher;
import com.proyecto.utils.Validaciones;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Optional;
import java.util.Random;

/**
 * Capa de servicio para los usuarios.
 */

@Service
public class UsuariosService extends ResponseEntityExceptionHandler {

    @Autowired
    UsuariosRepository usuariosRepository;

    @Autowired
    ImagenesService imagenesService;

    @Autowired
    SendMail sendMail;

    public void verificarUser(UserLoginDto entrada, HttpServletRequest request) {
        Optional<UsuarioModels> user = usuariosRepository.loguearUsuario(entrada.getUser(), entrada.getClave());
        if (user.isPresent()) {
            if (!user.get().getMailVerificado()) {
                throw new ApiException(300, "Se creo una cuenta nueva");
            }
            request.getSession(true).setAttribute("user", entrada.getUser());
        } else {
            throw new ApiException(401, "Credenciales invalidas.");
        }
    }

    public UserResponseDto obtenerUsuario(HttpServletRequest request) {
        String userInput = "";
        if (request.getSession(false) != null) {
            userInput = (String) request.getSession(false).getAttribute("user");
        } else {
            throw new ApiException(401, Constantes.ERROR_NO_AUTORIZADO);
        }
        Optional<UsuarioModels> usuario = usuariosRepository.obtenerUsuario(userInput);
        if (usuario.isPresent()) {
            UserResponseDto salida = new UserResponseDto();
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
            throw new ApiException(404, Constantes.ERROR_NO_EXISTE);
        }
    }

    public Integer crearUsuario(UserCreateDto entrada) throws NoSuchAlgorithmException {
        if (Validaciones.validarDatosCreateUser(entrada)) {
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
            usuario.setAdmin(false);
            usuario.setFechaRegistro(new Date());
            usuario.setMailVerificado(false);
            Random rand = new Random();
            Integer codigoRandom = rand.nextInt(999999);
            usuario.setCodigoVerificacion(codigoRandom.toString());
            usuario = usuariosRepository.save(usuario);

            try {
                sendMail.enviarMail(entrada.getMail(), "Ingrese al siguiente enlace para activar su cuenta: \n" +
                        "http://localhost:8080/usuarios/verificarMail/" + entrada.getUser() + "/" + codigoRandom);
            } catch (Exception error) {
                usuariosRepository.delete(usuario);
                throw new ApiException(500, Constantes.ERROR_FALLO_MAIL);
            }

            return usuario.getIdUsuario();
        } else {
            throw new ApiException(400, Constantes.ERROR_DATOS_INVALIDOS);
        }
    }

    public void borrarUsuario(int idUsuario) {
        if (!usuariosRepository.existsById(idUsuario)) {
            throw new ApiException(404, Constantes.ERROR_NO_EXISTE);
        } else {
            usuariosRepository.deleteById(idUsuario);
        }
    }

    public int actualizarUsuario(HttpServletRequest request, UserModifyDto entrada) {
        String userInput = "";
        if (request.getSession(false) != null) {
            userInput = (String) request.getSession(false).getAttribute("user");
        } else {
            throw new ApiException(401, Constantes.ERROR_NO_AUTORIZADO);
        }
        Optional<UsuarioModels> userDB = usuariosRepository.obtenerUsuario(userInput);
        if (userDB.isPresent()) {
            UsuarioModels user = userDB.get();
            if (entrada.getUser() != null) {
                if (user.getClave().equals(entrada.getClave())
                        && entrada.getUser().length() >= 8 && entrada.getUser().length() <= 30) {
                    request.getSession(true).setAttribute("user", entrada.getUser());
                    user.setUser(entrada.getUser());
                } else {
                    throw new ApiException(400, Constantes.ERROR_DATOS_INVALIDOS);
                }
            }
            if (entrada.getMail() != null) {
                if (user.getClave().equals(entrada.getClave()) && entrada.getMail().length() <= 100) {
                    user.setMail(entrada.getMail());
                } else {
                    throw new ApiException(400, Constantes.ERROR_DATOS_INVALIDOS);
                }
            }
            if (entrada.getNuevaClave() != null) {
                if (user.getClave().equals(entrada.getClave()) && entrada.getNuevaClave().length() >= 8 &&
                        entrada.getNuevaClave().length() <= 50) {
                    user.setClave(entrada.getNuevaClave());
                } else {
                    throw new ApiException(400, Constantes.ERROR_DATOS_INVALIDOS);
                }
            }
            usuariosRepository.save(user);
            return user.getIdUsuario();
        } else {
            throw new ApiException(404, Constantes.ERROR_NO_EXISTE);
        }
    }

    public UsuarioModels actualizarFotoPerfil(HttpServletRequest request, UserPhotoDto body) throws NoSuchAlgorithmException {
        String userInput = "";
        if (request.getSession(false) != null) {
            userInput = (String) request.getSession(false).getAttribute("user");
        } else {
            throw new ApiException(401, Constantes.ERROR_NO_AUTORIZADO);
        }
        Optional<UsuarioModels> userDB = usuariosRepository.obtenerUsuario(userInput);
        if (userDB.isPresent()) {
            byte[] hash = Sha1Hasher.hashBytes(body.getImagen());
            Optional<ImagenModels> imagen = imagenesService.obtenerImagenPorHash(hash);
            if (imagen.isPresent()) {
                userDB.get().setImagenPerfil(imagen.get());
            } else {
                userDB.get().setImagenPerfil(imagenesService.cargarImagen(body.getImagen(), userDB.get()));
            }
            usuariosRepository.save(userDB.get());
            return userDB.get();
        } else {
            throw new ApiException(404, Constantes.ERROR_NO_EXISTE);
        }
    }

    public Boolean verificarCodigoMail(String usuario, String codigo) {
        Optional<UsuarioModels> user = usuariosRepository.obtenerUsuario(usuario);
        if (user.isPresent()) {
            if (user.get().getCodigoVerificacion().equals(codigo)) {
                user.get().setMailVerificado(true);
                usuariosRepository.save(user.get());
                return true;
            } else {
                return false;
            }
        } else {
            throw new ApiException(404, Constantes.ERROR_NO_EXISTE);
        }
    }

    public void verificarCodigoMailReintento(VerificacionCodigoDto body) throws Exception {
        Optional<UsuarioModels> user = usuariosRepository.loguearUsuario(body.getUser(), body.getClave());
        if (user.isPresent()) {
            sendMail.enviarMail(user.get().getMail(), "Ingrese al siguiente enlace para activar su cuenta: \n" +
                    "http://localhost:8080/usuarios/verificarMail/" + body.getUser() + "/" + user.get().getCodigoVerificacion());
        } else {
            throw new ApiException(404, Constantes.ERROR_NO_EXISTE);
        }
    }

    public void restaurarClave(UserRestorePasswordDto body) throws Exception {
        Optional<UsuarioModels> user = usuariosRepository.recuperarUsuarioMail(body.getMail());
        if (user.isPresent()) {
            Random rand = new Random();
            Integer codigoNuevo = rand.nextInt(999999);
            Integer claveNueva = rand.nextInt(99999999);
            user.get().setCodigoVerificacion(codigoNuevo.toString());
            user.get().setClaveTemporal(claveNueva.toString());
            usuariosRepository.save(user.get());
            sendMail.enviarMail(body.getMail(), "Clave temporal:" + claveNueva + ". \n"
                    + "Ingrese al siguiente enlace para activar la clave temporal: http://localhost:8080/usuarios/activarClave/"
                    + user.get().getUser() + "/" + codigoNuevo + " \n"
                    + "Luego de activar la nueva clave, ingrese a su cuenta y cambie la clave temporal por seguridad.");
        }
    }

    public Boolean activarClave(String usuario, String codigo) {
        Optional<UsuarioModels> user = usuariosRepository.obtenerUsuario(usuario);
        if (user.isPresent()) {
            if (user.get().getCodigoVerificacion().equals(codigo)) {
                user.get().setClave(user.get().getClaveTemporal());
                usuariosRepository.save(user.get());
                return true;
            } else {
                return false;
            }
        } else {
            throw new ApiException(404, Constantes.ERROR_NO_EXISTE);
        }
    }

}
