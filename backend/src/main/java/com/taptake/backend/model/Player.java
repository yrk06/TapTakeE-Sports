package com.taptake.backend.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "Jogador")
public class Player implements Serializable {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", updatable = false, nullable = false, columnDefinition = "VARCHAR(36)")
    @Type(type = "uuid-char")
    private UUID idJogador;

    @Column(nullable = false)
    private UUID idEquipe;
    //alterar para foreign key dps quando tiver a parte da equipe criada
    /*
    * @ManyToOne
    * @JoinColumn(name="idEquipe", referencedColumnName="idEquipe"
    * private Equipe equipe
    * */

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
