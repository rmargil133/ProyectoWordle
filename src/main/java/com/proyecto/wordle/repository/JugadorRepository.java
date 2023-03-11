package com.proyecto.wordle.repository;

import com.proyecto.wordle.model.Jugador;
import org.springframework.data.jpa.repository.JpaRepository;
public interface JugadorRepository extends JpaRepository<Jugador, Long>{
}
