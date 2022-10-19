package com.taptake.backend.DRO;

import com.taptake.backend.model.Championship;

import java.util.List;

public class MatchDRO {
    private String idPartida;

    private Championship championship;

    private List<String> teamList;

    public String getIdPartida() {
        return idPartida;
    }

    public void setIdPartida(String idPartida) {
        this.idPartida = idPartida;
    }

    public Championship getChampionship() {
        return championship;
    }

    public void setChampionship(Championship championship) {
        this.championship = championship;
    }

    public List<String> getTeamList() {
        return teamList;
    }

    public void setTeamList(List<String> teamList) {
        this.teamList = teamList;
    }
}
