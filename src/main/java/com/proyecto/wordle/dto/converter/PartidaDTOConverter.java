package com.proyecto.wordle.dto.converter;

import com.proyecto.wordle.model.Partida;
import com.proyecto.wordle.dto.PartidaDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PartidaDTOConverter {
    private final ModelMapper modelMapper;

    public PartidaDTO convertToDTO (Partida partida){
        return modelMapper.map(partida, PartidaDTO.class);
    }

}