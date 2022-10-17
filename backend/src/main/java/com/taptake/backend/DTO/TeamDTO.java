package com.taptake.backend.DTO;

public class TeamDTO {
    private String idJogo;
    private String idOrg;
    private String nomeTime;

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
