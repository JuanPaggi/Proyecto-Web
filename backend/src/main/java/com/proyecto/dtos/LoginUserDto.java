package com.proyecto.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class LoginUserDto {

    @JsonProperty("user")
    private String user;

    @JsonProperty("clave")
    private String clave;

}
