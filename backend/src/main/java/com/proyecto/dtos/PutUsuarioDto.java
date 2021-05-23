package com.proyecto.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class PutUsuarioDto {

    @JsonProperty ("user")
    private String user;

    @JsonProperty ("mail")
    private String mail;

    @JsonProperty ("clave")
    private String clave;

    @JsonProperty ("nuevaClave")
    private String nuevaClave;
}
