package com.taptake.backend.model;

import com.taptake.backend.DRO.TeamDRO;
import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
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


    public TeamDRO generateDRO(){
        List<String> idPartidas = new ArrayList<>();
        List<String> idJogadores = new ArrayList<>();
        for(Match m : partidas){
            idPartidas.add(m.getIdPartida().toString());
        }
        for(Player p : players){
            idJogadores.add(p.getIdJogador().toString());
        }
        return new TeamDRO(idEquipe.toString(), game.getIdJogo().toString(),org.getIdOrg().toString(),nomeTime,idPartidas,idJogadores );
    }
}
