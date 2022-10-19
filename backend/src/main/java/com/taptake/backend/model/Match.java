package com.taptake.backend.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "Partida")
public class Match implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Type(type = "uuid-char")
    private UUID idPartida;

    @ManyToOne
    @JoinColumn(name = "idCampeonato")
    private Championship championship;

    @ManyToMany
    @JoinTable(name = "ParticipacaoPartida", joinColumns = @JoinColumn(name = "idEquipe"), inverseJoinColumns = @JoinColumn(name="idPartida", nullable = true))
    private Set<Team> equipes;

    public Set<Team> getEquipes() {
        return equipes;
    }

    public void setEquipes(Set<Team> equipes) {
        this.equipes = equipes;
    }

    public UUID getIdPartida() {
        return idPartida;
    }

    public void setIdPartida(UUID idPartida) {
        this.idPartida = idPartida;
    }

    public Championship getChampionship() {
        return championship;
    }

    public void setChampionship(Championship championship) {
        this.championship = championship;
    }
}
