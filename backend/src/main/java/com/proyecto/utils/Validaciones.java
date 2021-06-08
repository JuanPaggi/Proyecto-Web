package com.proyecto.utils;

import com.proyecto.dtos.user.UserCreateDto;
import com.proyecto.exceptions.ApiException;

import javax.servlet.http.HttpServletRequest;

public class Validaciones {

    public static String obtenerUserLogin(HttpServletRequest request){
        if (request.getSession(false) != null) {
            return (String) request.getSession(false).getAttribute("user");
        } else {
            throw new ApiException(401, Constantes.ERROR_NO_AUTORIZADO);
        }
    }

    public static boolean validarDatosCreateUser(UserCreateDto entrada) {
        return entrada.getUser().length() >= 8 && entrada.getUser().length() <= 30
                && entrada.getClave().length() >= 8 && entrada.getClave().length() <= 50
                && entrada.getMail().length() <= 100 && entrada.getMail().contains("@") && !entrada.getMail().contains("+");
    }

}
