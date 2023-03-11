package com.proyecto.wordle.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PartidaJugador extends RuntimeException{
    private static final long serialVersionUID = 43874481117560211L;

    public PartidaJugador(Long id){
        super("No se ha podido encontrar la partida con el jugador con el ID: " + id);
    }
}
