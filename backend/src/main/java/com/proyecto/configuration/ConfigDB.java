package com.proyecto.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

/**
 * Configuramos el properties con los datos de la DB.
 */

@Configuration
public class ConfigDB {

    @Configuration
    @Profile("default")
    @PropertySources(value = {
            @PropertySource("classpath:database.properties"),
    })
    static class ProfileDefault {

    }

}
