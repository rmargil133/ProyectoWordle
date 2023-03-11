package com.proyecto.wordle.repository;

import com.proyecto.wordle.model.Partida;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
public interface PartidaRepository extends JpaRepository<Partida,Long>{
    List<Partida> findAllByJugador_IdOrderByPuntosDesc(Long id);


}