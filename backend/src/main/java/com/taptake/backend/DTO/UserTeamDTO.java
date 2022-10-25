package com.taptake.backend.DTO;

import java.util.List;

public class UserTeamDTO {

    private String idJogo;
    private List<String> players;

    public String getIdJogo() {
        return idJogo;
    }

    public void setIdJogo(String idJogo) {
        this.idJogo = idJogo;
    }

    public List<String> getPlayers() {
        return players;
    }

    public void setPlayers(List<String> idEquipes) {
        this.players = idEquipes;
    }

}
