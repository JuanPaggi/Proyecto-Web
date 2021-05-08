package com.proyecto.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class GetEtiquetaDto {

    @JsonProperty("idEtiqueta")
    private int idEtiqueta;

    @JsonProperty("etiqueta")
    private String etiqueta;

}
