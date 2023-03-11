package com.proyecto.wordle.controller;

import com.proyecto.wordle.dto.JugadorDTO;
import com.proyecto.wordle.dto.ModJugadorDTO;
import com.proyecto.wordle.dto.converter.JugadorDTOConverter;
import com.proyecto.wordle.errors.JugadorNotFound;
import com.proyecto.wordle.model.Equipo;
import com.proyecto.wordle.model.Jugador;
import com.proyecto.wordle.repository.EquipoRepository;
import com.proyecto.wordle.repository.JugadorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class JugadorController {
    private final JugadorRepository jugadorRepository;
    private final EquipoRepository equipoRepository;
    private final JugadorDTOConverter jugadorDTOConverter;

    /**
     * Se obtienen todos los jugadores.
     *
     * @return lista de los jugadores.
     */
    @GetMapping("/jugadores")
    public ResponseEntity<List<?>> getAlljugadores() {
        List<Jugador> jugadores = jugadorRepository.findAll();
        if(jugadores.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        else{
            List<JugadorDTO> dtoList =
                    jugadores.stream().map(jugadorDTOConverter::convertToDTO).collect(Collectors.toList());
            return ResponseEntity.ok(dtoList);
        }
    }

    /**
     * Obtenemos un jugador con su Id.
     *
     * @param id Identidicación del jugador.
     * @return Jugador | Error 404
     */
    @GetMapping("/jugador/{id}")
    public Jugador getJugadorbyId(@PathVariable Long id){
        return jugadorRepository.findById(id)
                .orElseThrow(() -> new JugadorNotFound(id));
    }


    /**
     * Eliminamos un jugador por su id.
     *
     * @param id Identidicación del jugador.
     * @return Código 204.
     */
    @DeleteMapping("/jugador/{id}")
    public ResponseEntity<Void> deleteJugadorById(@PathVariable Long id){
        Jugador jugador = jugadorRepository.findById(id).orElseThrow(() -> new JugadorNotFound(id));
        jugadorRepository.delete(jugador);

        return ResponseEntity.noContent().build();
    }

    /**
     * Creamos un nuevo jugador.
     *
     * @param nuevojug Datos del jugador nuevo.
     * @return jugador nuevo.
     */
    @PostMapping("/jugadores")
    public ResponseEntity<?> newJugador(@RequestBody ModJugadorDTO nuevojug){
        Jugador njugador = new Jugador();
        Equipo equipo = null;
        njugador.setNombre(nuevojug.getNombre());
        njugador.setAvatar(nuevojug.getAvatar());
        if(nuevojug.getIdEquipo() != null){
            equipo = equipoRepository.findById(nuevojug.getIdEquipo()).orElse(null);
        }
        njugador.setEquipo(equipo);
        njugador.setPuntos(Math.toIntExact(nuevojug.getPuntos()));
        return ResponseEntity.status(HttpStatus.CREATED).body(jugadorRepository.save(njugador));

    }

    /**
     * Actualizamos un jugador.
     *
     * @param newJugador .
     * @param id Identificador del jugador.
     * @return Jugador actualizado | Código 404 si no lo encuentra.
     */
    @PutMapping("/jugador/{id}")
    public Jugador updateJugador(@RequestBody ModJugadorDTO newJugador, @PathVariable Long id){
        Equipo equipo = null;
        if (newJugador.getIdEquipo() != null){
            equipo = equipoRepository.findById(newJugador.getIdEquipo()).orElse(null);
        }

        final Equipo nEquipo = equipo;
        return jugadorRepository.findById(id).map(jug -> {
            jug.setNombre(newJugador.getNombre());
            jug.setAvatar(newJugador.getAvatar());
            jug.setEquipo(nEquipo);
            jug.setPuntos(Math.toIntExact(newJugador.getPuntos()));
            return jugadorRepository.save(jug);
        }).orElseThrow(() -> new JugadorNotFound(id));
    }
}
