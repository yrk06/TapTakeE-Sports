package com.taptake.backend.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "ParticipacaoCampeonato")
public class ChampionshipParticipation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Type(type = "uuid-char")
    private UUID idParticipacaoCampeonato;

    @ManyToOne
    @JoinColumn(name = "idCampeonato")
    private Championship championship;

    @OneToOne
    @JoinColumn(name = "idEquipe")
    private Team team;

    @Column(nullable = false)
    private int posicao;

    public int getPosicao() {
        return posicao;
    }

    public void setPosicao(int posicao) {
        this.posicao = posicao;
    }

    public UUID getidParticipacaoCampeonato() {
        return idParticipacaoCampeonato;
    }

    public void setidParticipacaoCampeonato(UUID id) {
        this.idParticipacaoCampeonato = id;
    }

    public Championship getChampionship() {
        return championship;
    }

    public void setChampionship(Championship championship) {
        this.championship = championship;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
