package com.proyecto.wordle.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EquipoNotFound extends RuntimeException {
    private static final  long serialVersionUID = 43876691117760211L;

    public EquipoNotFound(Long id){
        super("No se ha podido encontrar al equipo con el ID: " + id);
    }
}
