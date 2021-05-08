package com.proyecto.utils;

import lombok.Data;
import lombok.Getter;

/**
 *  Excepcion para toda la aplicacion con codigo y mensaje.
 */

@Data
@Getter
public class ApiException extends RuntimeException {

    protected final int code;

    public ApiException(int code, String mensaje){
        super(mensaje);
        this.code = code;
    }

}
