package com.taptake.backend.DTO;

import java.util.List;

public class MatchDTO {

    private String idCampeonato;

    private List<String> idEquipes;



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
