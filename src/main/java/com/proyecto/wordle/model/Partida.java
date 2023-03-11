package com.proyecto.wordle.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Entity
public class Partida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="id_jugador")
    @JsonBackReference
    private Jugador jugador;

    @ManyToOne
    @JoinColumn(name="id_juego")
    @JsonBackReference
    private Juego juego;

    @Enumerated(EnumType.STRING)
    @Column(name="dificultad")
    private Dificultad dificultad;

    private  Long Intentos_max;
    private Long Puntos;
    private String Palabra_jugada;
    private LocalDateTime Datetime;

}
