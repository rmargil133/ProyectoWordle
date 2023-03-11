package com.proyecto.wordle.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PalabraNoEncontrada extends RuntimeException{
    private static final  long serialVersionUID = 43838693337760211L;

    public PalabraNoEncontrada(String cadena){
        super("No hay palabras que contengan la cadena: " + cadena);
    }

}
