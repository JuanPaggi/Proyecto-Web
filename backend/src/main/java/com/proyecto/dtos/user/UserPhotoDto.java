package com.proyecto.dtos.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * DTO para actualizar al foto de perfil del usuario.
 */

@Getter
@Setter
public class UserPhotoDto {

    @JsonProperty("imagen")
    @NotNull
    private byte[] imagen;

}
