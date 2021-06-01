package com.proyecto.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Utilizamos el algoritmo SHA-1 para generar un hash para las imagenes.
 */

public class Sha1Hasher {

    public static byte[] hashBytes(byte[] input) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        return md.digest(input);
    }

}
