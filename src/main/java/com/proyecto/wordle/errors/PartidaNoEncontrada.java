package com.proyecto.wordle.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PartidaNoEncontrada extends RuntimeException {
    private static final long serialVersionUID = 43874491117560211L;

    public PartidaNoEncontrada(Long id){
        super("No se ha podido encontrar la partida con el ID: " + id);
    }
}
