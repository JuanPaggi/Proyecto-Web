package com.proyecto.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * DTO para actualizar al foto de perfil del usuario.
 */

@Getter
@Setter
public class UserPhotoDto {

    @JsonProperty("imagen")
    private byte[] imagen;

}
