package com.proyecto.wordle.controller;


import com.proyecto.wordle.errors.JuegoNoEncontrado;
import com.proyecto.wordle.errors.JugadorNoEncontrado;
import com.proyecto.wordle.model.Dificultad;
import com.proyecto.wordle.model.Juego;
import com.proyecto.wordle.model.Jugador;
import com.proyecto.wordle.model.Partida;
import com.proyecto.wordle.repository.JuegoRepository;
import com.proyecto.wordle.repository.JugadorRepository;
import com.proyecto.wordle.repository.PartidaRepository;
import com.proyecto.wordle.dto.converter.PartidaDTOConverter;
import com.proyecto.wordle.dto.PartidaModDTO;
import com.proyecto.wordle.dto.PartidaDTO;
import com.proyecto.wordle.errors.PartidaJugador;
import com.proyecto.wordle.errors.PartidaNoEncontrada;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class PartidaController {

    private final PartidaRepository partidaRepository;
    private final JuegoRepository juegoRepository;
    private final JugadorRepository jugadorRepository;

    private final PartidaDTOConverter partidaDTOConverter;



    /**
     * Obtenemos todas las partidas.
     *
     * @return listado de partidas.
     */
    @GetMapping("/partidas")
    public ResponseEntity<List<?>> getAllPlays(){
        List<Partida> plays = partidaRepository.findAll();
        if(plays.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            List<PartidaDTO> dtoList =
                    plays.stream().map(partidaDTOConverter::convertToDTO).collect(Collectors.toList());
            return ResponseEntity.ok(dtoList);
        }

    }

    /**
     * Obtenemos una partida por su Id.
     *
     * @param id Identificador de la partida.
     * @return partida | Error 404 si no encuentra la partida.
     */
    @GetMapping("/partida/{id}")
    public Partida getPartidaById(@PathVariable Long id){
        return partidaRepository.findById(id)
                .orElseThrow(() -> new PartidaNoEncontrada(id));
    }


    /**
     * Obtenemos una lista de partidas ordenadas por puntos con la id de un jugador en concreto.
     *
     * @param id Identificador del jugador.
     * @return lista partidas | Error 404.
     */
    @GetMapping("/partidas/jugador/{id}")
    public ResponseEntity<List<?>>getPartidasByJugadorId(@PathVariable Long id){
        List<Partida> partidas = partidaRepository.findAllByJugador_IdOrderByPuntosDesc(id);

        if(partidas.isEmpty()){
            throw new PartidaJugador(id);
        }
        else{
            List<PartidaDTO> dtoList =
                    partidas.stream().map(partidaDTOConverter::convertToDTO).collect(Collectors.toList());
            return ResponseEntity.ok(dtoList);
        }
    }


    /**
     * Eliminamos una partida por su Id.
     *
     * @param id
     * @return Código 204
     */
    @DeleteMapping("/partida/{id}")
    public ResponseEntity<Void> deletePartidaById(@PathVariable Long id){
        Partida partida = partidaRepository.findById(id).orElseThrow(() -> new PartidaNoEncontrada(id));
        partidaRepository.delete(partida);
        return ResponseEntity.noContent().build();
    }


    /**
     * Creamos una nueva partida.
     *
     * @param nuevapartida
     * @return nueva partida.
     */
    @PostMapping("/partidas")
    public ResponseEntity<?> newPartida(@RequestBody PartidaDTO nuevapartida){
        Jugador jugador = jugadorRepository.findById(nuevapartida.getId_jugador()).orElseThrow(() -> new JugadorNoEncontrado(nuevapartida.getId_jugador()));
        Juego juego = juegoRepository.findById(nuevapartida.getId_juego()).orElseThrow(() -> new JuegoNoEncontrado(nuevapartida.getId_juego()));
        Partida npartida = new Partida();
        npartida.setPalabra_jugada(nuevapartida.getPalabra_jugada());
        npartida.setDatetime(LocalDateTime.now());
        npartida.setIntentos_max(nuevapartida.getIntentos_max());
        npartida.setPuntos(nuevapartida.getPuntos());
        npartida.setJugador(jugador);
        npartida.setJuego(juego);
        npartida.setDificultad(Dificultad.valueOf(nuevapartida.getDificultad()));
        return ResponseEntity.status(HttpStatus.CREATED).body(partidaRepository.save(npartida));
    }


    /**
     * Modificamos una partida.
     *
     * @param modPartida
     * @param id Identificador de la partida.
     * @return partida modificada | Código 404.
     */
    @PutMapping("/partida/{id}")
    public Partida updatePartida(@RequestBody PartidaModDTO modPartida, @PathVariable Long id){
        final Jugador jugador = jugadorRepository.findById(modPartida.getId_jugador()).orElseThrow(() -> new JugadorNoEncontrado(id));
        final Juego juego = juegoRepository.findById(modPartida.getId_juego()).orElseThrow(() -> new JuegoNoEncontrado(modPartida.getId_juego()));

        return partidaRepository.findById(id).map(partida -> {
            partida.setPalabra_jugada(modPartida.getPalabra_jugada());
            partida.setJuego(juego);
            partida.setJugador(jugador);
            partida.setIntentos_max(modPartida.getIntentos_max());
            partida.setPuntos(modPartida.getPuntos());
            partida.setDificultad(Dificultad.valueOf(modPartida.getDificultad()));
            return partidaRepository.save(partida);
        }).orElseThrow(() -> new PartidaNoEncontrada(id));
    }
}
