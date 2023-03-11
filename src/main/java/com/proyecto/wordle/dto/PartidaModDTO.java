package com.proyecto.wordle.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PartidaModDTO {

    private LocalDateTime Datetime;
    private Long Intentos_max;
    private String Dificultad;
    private Long Puntos;
    private Long id_juego;
    private Long id_jugador;

    private String Palabra_jugada;
}
