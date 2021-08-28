package com.proyecto.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.proyecto.dtos.user.UserNameResponseDto;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ImageResponseDto {

    @JsonProperty("imagen")
    private byte[] imagen;

    @JsonProperty("fechaSubida")
    private Date fechaSubida;

    @JsonProperty("usuario")
    private UserNameResponseDto usuario;

}
