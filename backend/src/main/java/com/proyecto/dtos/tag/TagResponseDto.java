package com.proyecto.dtos.tag;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * DTO para devolver los datos de una etiqueta.
 */

@Getter
@Setter
public class TagResponseDto {

    @JsonProperty("id_etiqueta")
    private int idEtiqueta;

    @NotNull
    @JsonProperty("etiqueta")
    private String etiqueta;

}
