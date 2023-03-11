package com.proyecto.wordle.configuracion;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Configuracion {
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
