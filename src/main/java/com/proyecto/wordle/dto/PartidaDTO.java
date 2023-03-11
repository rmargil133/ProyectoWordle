package com.proyecto.wordle.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PartidaDTO {
    private Long id;
    private LocalDateTime Datetime;
    private Long id_juego;
    private Long id_jugador;
    private String Palabra_jugada;
    private String Dificultad;
    private Long Intentos_max;
    private Long Puntos;
    private String Nombre;


}