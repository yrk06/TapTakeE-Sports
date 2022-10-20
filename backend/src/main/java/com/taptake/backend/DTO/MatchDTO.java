package com.taptake.backend.DTO;

import java.util.Date;
import java.util.List;

public class MatchDTO {

    private String idCampeonato;

    private List<String> idEquipes;

    private Date data;

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public List<String> getIdEquipes() {
        return idEquipes;
    }

    public void setIdEquipes(List<String> idEquipes) {
        this.idEquipes = idEquipes;
    }

    public String getIdCampeonato() {
        return idCampeonato;
    }

    public void setIdCampeonato(String idCampeonato) {
        this.idCampeonato = idCampeonato;
    }
}
