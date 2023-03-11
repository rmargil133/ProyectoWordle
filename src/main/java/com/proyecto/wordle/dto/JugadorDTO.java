package com.proyecto.wordle.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class JugadorDTO {
    private Long id;

    private String Nombre;

    private String Avatar;

    private Long id_equipo;

    private Long Puntos;

}
