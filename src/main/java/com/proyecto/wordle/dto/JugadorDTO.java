package com.proyecto.wordle.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class JugadorDTO {
    private Long id;

    private String nombre;

    private String avatar;

    private Long id_equipo;

    private Long puntos;

    private LocalDateTime last_mod;
}
