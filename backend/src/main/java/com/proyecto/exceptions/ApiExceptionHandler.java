package com.proyecto.exceptions;

import com.proyecto.dtos.ResponseDto;
import com.proyecto.utils.Constantes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;

/**
 * Esta clase captura todas las excepciones del proyecto.
 */

@RestControllerAdvice
public class ApiExceptionHandler {

    public static final Logger log = LoggerFactory.getLogger(ApiExceptionHandler.class);

    /**
     * Capturamos todas las excepciones controladas de la logica en la capa de Service.
     */

    @ExceptionHandler({ApiException.class})
    @ResponseBody
    public ResponseEntity<ResponseDto> apiExceptionType(ApiException e) {
        ResponseDto error = new ResponseDto(e.getCode(), e.getMessage());
        switch (e.getCode()) {
            case 300:
                log.error("Error:" + error.getDetalle());
                return new ResponseEntity<>(error, HttpStatus.MULTIPLE_CHOICES);
            case 400:
                log.error("Error:" + error.getDetalle());
                return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
            case 401:
                log.error("Error:" + error.getDetalle());
                return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
            case 404:
                log.error("Error:" + error.getDetalle());
                return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
            case 409:
                log.error("Error:" + error.getDetalle());
                return new ResponseEntity<>(error, HttpStatus.CONFLICT);
            default:
                log.error("Error:" + error.getDetalle(), e);
                return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Capturamos las excepciones de los request mal formados con los NotNull de los Dto.
     */

    @ExceptionHandler({MethodArgumentNotValidException.class, HttpRequestMethodNotSupportedException.class})
    @ResponseBody
    public ResponseEntity<ResponseDto> methodArgumentNotValidException() {
        ResponseDto error = new ResponseDto(400, Constantes.BAD_REQUEST);
        log.error("Error:" + error.getDetalle());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    /**
     * Capturamos las excepciones no controladas de la aplicacion.
     * Mostramos por consola el Stack del error.
     */

    @ExceptionHandler({NoSuchAlgorithmException.class, Exception.class})
    @ResponseBody
    public ResponseEntity<ResponseDto> fatalException(Exception e) {
        ResponseDto error = new ResponseDto(500, Constantes.ERROR_GENERAL);
        log.error("Error:" + error.getDetalle(), e);
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
