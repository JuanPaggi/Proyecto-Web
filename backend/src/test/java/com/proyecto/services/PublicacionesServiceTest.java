package com.proyecto.services;

import com.proyecto.dtos.publication.PublicationResponseDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@ActiveProfiles("test")
@SpringBootTest
public class PublicacionesServiceTest {

    @Autowired
    PublicacionesService publicacionesService;

    @Test
    @Transactional
    public void obtenerPublicaciones(){
        try{
            List<PublicationResponseDto> salida = publicacionesService.obtenerTodasPublicaciones();
            Assertions.assertTrue(salida.size() > 0);
        }catch (Exception e){
            Assertions.assertTrue(false);
        }
    }

}
