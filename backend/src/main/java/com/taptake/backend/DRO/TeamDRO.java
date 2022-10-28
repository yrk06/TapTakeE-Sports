package com.taptake.backend.DRO;

import java.util.List;

public class TeamDRO {
    private String id;
    private String idJogo;
    private String idOrg;
    private String nomeTime;
    private List<String> idPartidas;
    private List<String> idJogadores;

    public String getId() {
        return id;
    }

    public TeamDRO(String id, String idJogo, String idOrg, String nomeTime, List<String> idPartidas, List<String> idJogadores) {
        this.id = id;
        this.idJogo = idJogo;
        this.idOrg = idOrg;
        this.nomeTime = nomeTime;
        this.idPartidas = idPartidas;
        this.idJogadores = idJogadores;
    }


    public void setId(String id) {
        this.id = id;
    }

    public String getIdJogo() {
        return idJogo;
    }

    public void setIdJogo(String idJogo) {
        this.idJogo = idJogo;
    }

    public String getIdOrg() {
        return idOrg;
    }

    public void setIdOrg(String idOrg) {
        this.idOrg = idOrg;
    }

    public String getNomeTime() {
        return nomeTime;
    }

    public void setNomeTime(String nomeTime) {
        this.nomeTime = nomeTime;
    }

    public List<String> getIdPartidas() {
        return idPartidas;
    }

    public void setIdPartidas(List<String> idPartidas) {
        this.idPartidas = idPartidas;
    }

    public List<String> getIdJogadores() {
        return idJogadores;
    }

    public void setIdJogadores(List<String> idJogadores) {
        this.idJogadores = idJogadores;
    }
}
