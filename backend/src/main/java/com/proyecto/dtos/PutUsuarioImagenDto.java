package com.proyecto.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import javassist.CtBehavior;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class PutUsuarioImagenDto {


    @JsonProperty("imagen")
    private byte[] imagen;
}
