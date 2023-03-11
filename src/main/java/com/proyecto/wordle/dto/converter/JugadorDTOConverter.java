package com.proyecto.wordle.dto.converter;

import com.proyecto.wordle.model.Jugador;
import com.proyecto.wordle.dto.JugadorDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
@Component
@RequiredArgsConstructor
public class JugadorDTOConverter {
    private final ModelMapper modelMapper;

    public JugadorDTO convertToDTO (Jugador jugador){
        return modelMapper.map(jugador, JugadorDTO.class);
    }
}
