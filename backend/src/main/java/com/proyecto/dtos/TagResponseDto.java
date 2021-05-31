package com.proyecto.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * DTO para devolver los datos de una etiqueta.
 */

@Getter
@Setter
public class TagResponseDto {

    @JsonProperty("id_etiqueta")
    private int idEtiqueta;

    @JsonProperty("etiqueta")
    private String etiqueta;

}
