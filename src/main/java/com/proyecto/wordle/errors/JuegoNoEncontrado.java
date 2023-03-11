package com.proyecto.wordle.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class JuegoNoEncontrado extends RuntimeException {
    private static final  long serialVersionUID = 43838691117760211L;

    public JuegoNoEncontrado (Long id){
        super("No se ha podido encontrar el juego con el ID: " + id);
    }
}
