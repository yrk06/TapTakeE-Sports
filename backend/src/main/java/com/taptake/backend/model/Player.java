package com.taptake.backend.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "Jogador")
public class Player implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Type(type = "uuid-char")
    private UUID idJogador;


     @ManyToOne
     @JoinColumn(name="idEquipe", referencedColumnName="idEquipe")
     private Team team;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String cargo;

//    PARTICIPAÇOES PARTIDA

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
}
