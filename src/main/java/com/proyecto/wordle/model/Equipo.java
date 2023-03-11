package com.proyecto.wordle.model;

import lombok.*;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import javax.persistence.*;

import java.time.LocalDateTime;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "Equipo")
public class Equipo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idEquipo")
    private Long idEquipo;

    @Column(name = "Nombre", length = 45, nullable = false, unique = true)
    private String Nombre;

    @Column(name = "Puntos", nullable = false)
    private Integer Puntos = 0;

    @Column(name = "Logo", length = 45)
    private String Logo = "";
}