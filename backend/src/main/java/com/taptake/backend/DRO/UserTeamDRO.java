package com.taptake.backend.DRO;

import java.util.List;

public class UserTeamDRO {

    private String idEquipeUsuario;

    private String user;

    private String game;

    private List<PlayerDRO> players;

    private int points;

    public String getIdEquipeUsuario() {
        return idEquipeUsuario;
    }

    public void setIdEquipeUsuario(String idEquipeUsuario) {
        this.idEquipeUsuario = idEquipeUsuario;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public List<PlayerDRO> getPlayers() {
        return players;
    }

    public void setPlayers(List<PlayerDRO> players) {
        this.players = players;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

}
