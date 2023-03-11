package com.proyecto.wordle.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import javax.persistence.*;

import java.util.Set;


@Data
@Builder
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Entity
public class Juego {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idJuego;

    private String Nombre;
    private String Instrucciones;

    @Enumerated(EnumType.STRING)
    @Column(name="Dificultad", nullable = false)
    private Dificultad dificultad;

    private Long Intentos_max;

    @JsonManagedReference
    @OneToMany(mappedBy = "juego", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Partida> partidas;

}
