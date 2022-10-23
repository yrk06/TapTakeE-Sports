package com.taptake.backend.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "PlayerTimeUsuario")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idJogadorTimeUsuario")
public class PlayerUserTeam implements Serializable {

    @Id
    @GeneratedValue
    @Type(type = "uuid-char")
    private UUID idJogadorTimeUsuario;

    @ManyToOne
    @JoinColumn(name = "idEquipeUsuario")
    private UserTeam Userteam;

    @ManyToOne
    @JoinColumn(name = "idJogador")
    private Player player;

    @Column
    private Date dataEntrada;

    @Column(nullable = false)
    private Date dataSaida;

    public UUID getIdJogadorTimeUsuario() {
        return idJogadorTimeUsuario;
    }

    public void setIdJogadorTimeUsuario(UUID idJogadorTimeUsuario) {
        this.idJogadorTimeUsuario = idJogadorTimeUsuario;
    }

    public UserTeam getUserteam() {
        return Userteam;
    }

    public void setUserteam(UserTeam userteam) {
        this.Userteam = userteam;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Date getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(Date dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public Date getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(Date dataSaida) {
        this.dataSaida = dataSaida;
    }
}
