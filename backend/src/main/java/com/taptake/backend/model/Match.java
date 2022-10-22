package com.taptake.backend.model;

import com.taptake.backend.DRO.MatchDRO;
import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "Partida")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idPartida")
public class Match implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Type(type = "uuid-char")
    private UUID idPartida;

    @ManyToOne
    @JoinColumn(name = "idCampeonato")
    private Championship championship;

    @ManyToMany
    @JoinTable(name = "ParticipacaoPartida", joinColumns = @JoinColumn(name = "idPartida"), inverseJoinColumns = @JoinColumn(name = "idEquipe"))
    private Set<Team> equipes;

    @Column(nullable = false)
    private Date data;

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Set<Team> getEquipes() {
        return equipes;
    }


// PARTICIPAÇÕES PARTIDA
    public MatchDRO generateDRO(){
        MatchDRO matchDRO = new MatchDRO();
        List<String> teamList = new ArrayList<>();
        for (Team t : this.equipes){
            teamList.add(t.getIdEquipe().toString());
        }
        matchDRO.setTeamList(teamList.stream().toList());
        matchDRO.setIdPartida(this.idPartida.toString());
        matchDRO.setChampionship(this.championship);
        matchDRO.setData(this.data);
        return matchDRO;
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
