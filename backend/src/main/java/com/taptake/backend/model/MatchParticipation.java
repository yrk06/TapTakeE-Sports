package com.taptake.backend.model;


import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "ParticipacaoPartida")
public class MatchParticipation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Type(type = "uuid-char")
    private UUID idParticipacaoPartida;

    @OneToOne
    @JoinColumn(name = "idPartida")
    private Match match;

    @OneToOne
    @JoinColumn(name="IdEquipe")
    private Team team;

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }



    public UUID getIdParticipacaoPartida() {
        return idParticipacaoPartida;
    }

    public void setIdParticipacaoPartida(UUID idParticipacaoPartida) {
        this.idParticipacaoPartida = idParticipacaoPartida;
    }

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }

}
