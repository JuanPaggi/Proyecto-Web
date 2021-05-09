package com.proyecto.dtos;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data
@Getter
@Setter
public class PostPublicacionDto {

    @JsonProperty("descripcion")
    private String descripcion;

    @JsonProperty("titulo")
    private String titulo;

}
