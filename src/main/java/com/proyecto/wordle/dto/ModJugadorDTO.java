package com.proyecto.wordle.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter @Getter
public class ModJugadorDTO {

    private String Nombre;

    private String Avatar;

    private Long idEquipo;

    private Long Puntos;

}
