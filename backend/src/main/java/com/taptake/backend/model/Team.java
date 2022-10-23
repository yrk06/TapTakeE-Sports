package com.taptake.backend.model;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "Equipe")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idEquipe")
public class Team implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Type(type = "uuid-char")
    private UUID idEquipe;

    @ManyToOne
    @JoinColumn(name = "idJogo")
    private Game game;

    @ManyToOne
    @JoinColumn(name = "idOrg")
    private Organization org;

    @Column(nullable = false)
    private String nomeTime;

    @ManyToMany(mappedBy = "equipes")
    private Set<Match> partidas;

    @OneToMany
    @JoinColumn(name = "idEquipe")
    private Set<Player> players;

    public Set<Player> getPlayers() {
        return players;
    }

    public void setPlayers(Set<Player> players) {
        this.players = players;
    }

    public Set<Match> getPartidas() {
        return partidas;
    }

    public void setPartidas(Set<Match> partidas) {
        this.partidas = partidas;
    }

    public UUID getIdEquipe() {
        return idEquipe;
    }

    public void setIdEquipe(UUID idEquipe) {
        this.idEquipe = idEquipe;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Organization getOrg() {
        return org;
    }

    public void setOrg(Organization org) {
        this.org = org;
    }

    public String getNomeTime() {
        return nomeTime;
    }

    public void setNomeTime(String nomeTime) {
        this.nomeTime = nomeTime;
    }
}
