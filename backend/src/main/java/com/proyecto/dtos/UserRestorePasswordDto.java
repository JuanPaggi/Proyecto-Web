package com.proyecto.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * DTO para recibir los datos de una recuperacio nde mail.
 */

@Getter
@Setter
public class UserRestorePasswordDto {

    @JsonProperty("mail")
    private String mail;

}
