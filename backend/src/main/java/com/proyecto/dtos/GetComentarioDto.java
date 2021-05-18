package com.proyecto.dtos;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Data
@Getter
@Setter
public class GetComentarioDto {

    @JsonProperty("idComentario")
    private int idComentario;

    @JsonProperty("texto")
    private String texto;

    @JsonProperty("fechaCreacion")
    private Date fechaCreacion;

}
