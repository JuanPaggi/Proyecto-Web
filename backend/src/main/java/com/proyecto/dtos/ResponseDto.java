package com.proyecto.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * DTO para devolver un mensaje de servicio ejecutado con exito.
 */

@Getter
@Setter
public class ResponseDto {

    @JsonProperty("Codigo")
    private Integer codigo;

    @JsonProperty("Detalle")
    private String detalle;

    public ResponseDto(Integer codigo, String detalle) {
        this.codigo = codigo;
        this.detalle = detalle;
    }

    public static ResponseDto getInstanceOk() {
        return new ResponseDto(200, "Servicio ejecutado correctamente");
    }

}
