package com.taptake.backend.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
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

    @OneToMany(mappedBy = "userTeam")
    private Set<PlayerUserTeam> players;

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

    public Set<PlayerUserTeam> getPlayers() {
        return players;
    }

    public void setPlayers(Set<PlayerUserTeam> players) {
        this.players = players;
    }

    public List<Player> getActivePlayers() {
        List<Player> active = new LinkedList<Player>();

        for (PlayerUserTeam pteam : this.players) {
            if (pteam.getDataSaida() == null) {
                active.add(pteam.getPlayer());
            }
        }

        return active;
    }

    // We need to change this back into the controller, to allow persistance
    public void removeActivePlayer(Player p) {
        for (PlayerUserTeam pteam : this.players) {
            if (pteam.getPlayer() == p && pteam.getDataSaida() == null) {
                pteam.setDataSaida(new Date());
            }
        }
    }

    public void addActivePlayer(Player p) {
        for (PlayerUserTeam pteam : this.players) {
            if (pteam.getPlayer() == p && pteam.getDataSaida() == null) {
                return;
            }
        }
        PlayerUserTeam newPlayer = new PlayerUserTeam();
        newPlayer.setDataEntrada(new Date());
        newPlayer.setDataSaida(null);
        newPlayer.setPlayer(p);
        this.players.add(newPlayer);
    }

}
