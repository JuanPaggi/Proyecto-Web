package com.proyecto.exceptions;

import lombok.Getter;

/**
 *  Excepcion para toda la aplicacion con codigo y mensaje.
 */

@Getter
public class ApiException extends RuntimeException {

    protected final int code;

    public ApiException(int code, String mensaje){
        super(mensaje);
        this.code = code;
    }

}
