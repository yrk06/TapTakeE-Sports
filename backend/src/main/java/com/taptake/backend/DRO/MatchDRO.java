package com.taptake.backend.DRO;

import com.taptake.backend.model.Championship;

import java.util.Date;
import java.util.List;

public class MatchDRO {
    private String idPartida;

    private ChampionshipDRO championship;

    private List<String> teamList;

    private List<LeftMatchPerformanceDRO> players;

    private Date data;

    public String getIdPartida() {
        return idPartida;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public void setIdPartida(String idPartida) {
        this.idPartida = idPartida;
    }

    public ChampionshipDRO getChampionship() {
        return championship;
    }

    public void setChampionship(ChampionshipDRO championship) {
        this.championship = championship;
    }

    public List<String> getTeamList() {
        return teamList;
    }

    public void setTeamList(List<String> teamList) {
        this.teamList = teamList;
    }

    public List<LeftMatchPerformanceDRO> getPlayers() {
        return players;
    }

    public void setPlayers(List<LeftMatchPerformanceDRO> players) {
        this.players = players;
    }

}
