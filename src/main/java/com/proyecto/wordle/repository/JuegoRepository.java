package com.proyecto.wordle.repository;

import com.proyecto.wordle.model.Juego;
import org.springframework.data.jpa.repository.JpaRepository;
public interface JuegoRepository extends JpaRepository<Juego,Long> {
}

