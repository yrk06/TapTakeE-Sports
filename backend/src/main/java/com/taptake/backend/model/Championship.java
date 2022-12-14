package com.taptake.backend.model;

import com.taptake.backend.DRO.ChampionshipDRO;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "Campeonato")
public class Championship implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Type(type = "uuid-char")
    private UUID idCampeonato;

    @ManyToOne
    @JoinColumn(name = "idJogo")
    private Game game;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String localCampeonato;

    @Column(nullable = false)
    private int premiacao;

    @OneToMany(mappedBy = "championship")
    private Set<ChampionshipParticipation> participacoes;

    public ChampionshipDRO generateDRO() {
        List<String> lst = new ArrayList<>();
        for (ChampionshipParticipation cp : participacoes) {
            lst.add(cp.getTeam().getIdEquipe().toString());
        }
        return new ChampionshipDRO(idCampeonato.toString(), game.getIdJogo().toString(), nome, localCampeonato,
                premiacao, lst);
    }

    public Set<ChampionshipParticipation> getParticipacoes() {
        return participacoes;
    }

    public void setParticipacoes(Set<ChampionshipParticipation> participacoes) {
        this.participacoes = participacoes;
    }

    public UUID getIdCampeonato() {
        return idCampeonato;
    }

    public void setIdCampeonato(UUID idCampeonato) {
        this.idCampeonato = idCampeonato;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLocalCampeonato() {
        return localCampeonato;
    }

    public void setLocalCampeonato(String localCampeonato) {
        this.localCampeonato = localCampeonato;
    }

    public int getPremiacao() {
        return premiacao;
    }

    public void setPremiacao(int premiacao) {
        this.premiacao = premiacao;
    }
}
