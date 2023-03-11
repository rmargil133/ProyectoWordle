package com.proyecto.wordle.dto;

import  com.proyecto.wordle.model.Dificultad;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JuegoDTO {
    private Long id;
    private Dificultad Dificultad;
    private String Nombre;
    private String Instrucciones;
    private Integer Intentos_max;
}
