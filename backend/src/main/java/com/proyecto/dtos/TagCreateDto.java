package com.proyecto.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * DTO para crear una etiqueta.
 */

@Getter
@Setter
public class TagCreateDto {

    @JsonProperty("etiqueta")
    private String etiqueta;

}
