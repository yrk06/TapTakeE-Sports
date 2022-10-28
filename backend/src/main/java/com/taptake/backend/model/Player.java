package com.taptake.backend.model;

import org.hibernate.annotations.Type;

import com.taptake.backend.DRO.PlayerDRO;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "Jogador")
public class Player implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Type(type = "uuid-char")
    private UUID idJogador;

    @ManyToOne
    @JoinColumn(name = "idEquipe")
    private Team team;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String cargo;

    // PARTICIPAÇOES PARTIDA
    @OneToMany
    @JoinColumn(name = "idJogador")
    private Set<MatchPerformance> matchPerformances;

    public Set<MatchPerformance> getMatchPerformances() {
        return matchPerformances;
    }

    public void setMatchPerformances(Set<MatchPerformance> matchPerformances) {
        this.matchPerformances = matchPerformances;
    }

    public UUID getIdJogador() {
        return idJogador;
    }

    public void setIdJogador(UUID idJogador) {
        this.idJogador = idJogador;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public PlayerDRO generateDRO() {
        PlayerDRO playerDRO = new PlayerDRO();

        playerDRO.setCargo(cargo);
        playerDRO.setNome(nome);
        playerDRO.setIdEquipe(team.getIdEquipe().toString());
        playerDRO.setIdJogador(idJogador.toString());

        return playerDRO;
    }
}
