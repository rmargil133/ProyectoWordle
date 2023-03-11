package com.proyecto.wordle.model;

import lombok.*;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "Jugador")
public class Jugador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idJugador")
    private Long idJugador;

    @Column(name = "Admin", columnDefinition = "TINYINT(1)", nullable = false)
    private Boolean Admin = false;

    @Column(name = "Nombre", length = 45, nullable = false)
    private String Nombre;

    @Column(name = "Avatar", length = 45)
    private String Avatar = "";

    @Column(name = "Puntos", nullable = false)
    private Integer Puntos = 0;

    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name="id_equipo")
    private Equipo equipo = null;
}