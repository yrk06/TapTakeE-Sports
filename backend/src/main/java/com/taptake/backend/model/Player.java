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

    // "Jogador" idEquipe foreign key has been changed from "NOT NULL" into a
    // possible null
    // We forgot to implement the Equipe model before these ones
    // #BlameGabu
    @Column(nullable = true)
    private UUID idEquipe;
    // alterar para foreign key dps quando tiver a parte da equipe criada
    /*
     * @ManyToOne
     * 
     * @JoinColumn(name="idEquipe", referencedColumnName="idEquipe")
     * private Equipe equipe
     */

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String cargo;

    public UUID getIdJogador() {
        return idJogador;
    }

    public void setIdJogador(UUID idJogador) {
        this.idJogador = idJogador;
    }

    public UUID getIdEquipe() {
        return idEquipe;
    }

    public void setIdEquipe(UUID idEquipe) {
        this.idEquipe = idEquipe;
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