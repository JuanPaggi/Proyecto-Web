package com.proyecto.dtos.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * DTO para recibir los datos de una recuperacio nde mail.
 */

@Getter
@Setter
public class UserRestorePasswordDto {

    @JsonProperty("mail")
    @NotNull
    private String mail;

}
