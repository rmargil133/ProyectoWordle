package com.proyecto.wordle.dto.converter;

import com.proyecto.wordle.dto.JuegoDTO;
import com.proyecto.wordle.model.Juego;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JuegoDTOConverter {
    private final ModelMapper modelMapper;

    public JuegoDTO convertToDTO(Juego juego){
        return modelMapper.map(juego, JuegoDTO.class);
    }
}
