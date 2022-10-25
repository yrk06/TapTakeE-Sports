package com.taptake.backend.DTO;

import java.util.List;

public class TeamDTO {
    private String idJogo;
    private String idOrg;
    private String nomeTime;

    private List<String> idJogadores;


    public List<String> getIdJogadores() {
        return idJogadores;
    }

    public void setIdJogadores(List<String> idJogadores) {
        this.idJogadores = idJogadores;
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
}
