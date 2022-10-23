package com.taptake.backend.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "TimeUsuario")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idEquipeUsuario")
public class UserTeam implements Serializable {

    @Id
    @GeneratedValue
    @Type(type = "uuid-char")
    private UUID idEquipeUsuario;

    @ManyToOne
    @JoinColumn(name = "idUsuario")
    private User user;

    @OneToOne
    @JoinColumn(name = "idJogo")
    private Game game;

    public UUID getIdEquipeUsuario() {
        return idEquipeUsuario;
    }

    public void setIdEquipeUsuario(UUID idEquipeUsuario) {
        this.idEquipeUsuario = idEquipeUsuario;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

}
