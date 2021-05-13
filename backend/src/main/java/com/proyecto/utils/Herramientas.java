package com.proyecto.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Herramientas {

    public static final Logger log = LoggerFactory.getLogger(Herramientas.class);

    public static boolean comprobarNumero(String cadena){


        try {
            Integer.parseInt(cadena);
            return true;
        } catch (Exception error){
            log.error("La cadena no es un entero. ");
            return false;
        }
    }
}
