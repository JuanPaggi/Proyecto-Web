package com.proyecto.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ComentarioDto {

    @JsonProperty("texto")
    private String texto;

    @JsonProperty("id_publicacion")
    private int idPublicacion;
}
