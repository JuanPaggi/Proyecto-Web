package com.proyecto.utils;

import com.proyecto.dtos.UserCreateDto;

public class Validaciones {

    public static boolean validarDatosCreateUser(UserCreateDto entrada) {
        return entrada.getUser().length() >= 8 && entrada.getUser().length() <= 30
                && entrada.getClave().length() >= 8 && entrada.getClave().length() <= 50
                && entrada.getMail().length() <= 100 && entrada.getMail().contains("@") && !entrada.getMail().contains("+");
    }

}
