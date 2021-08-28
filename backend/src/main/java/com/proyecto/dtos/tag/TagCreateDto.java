package com.proyecto.dtos.tag;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * DTO para crear una etiqueta.
 */

@Getter
@Setter
public class TagCreateDto {

    @JsonProperty("etiqueta")
    @NotNull
    private String etiqueta;

}
