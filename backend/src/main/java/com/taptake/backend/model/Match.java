package com.taptake.backend.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "Partida")
public class Match implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Type(type = "uuid-char")
    private UUID idPartida;

    @ManyToOne
    @JoinColumn
    private UUID idCampeonato;

    public UUID getIdPartida() {
        return idPartida;
    }

    public void setIdPartida(UUID idPartida) {
        this.idPartida = idPartida;
    }

    public UUID getIdCampeonato() {
        return idCampeonato;
    }

    public void setIdCampeonato(UUID idCampeonato) {
        this.idCampeonato = idCampeonato;
    }
}
