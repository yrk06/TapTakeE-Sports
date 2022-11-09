package com.taptake.backend.DRO;

import java.util.List;

public class ChampionshipDRO {
    private String idCampeonato;
    private String idGame;
    private String nome;
    private String localCampeonato;

    private int premiacao;
    private List<String> idEquipes;

    public ChampionshipDRO(String idCampeonato, String idGame, String nome, String localCampeonato, int premio,
            List<String> idEquipes) {
        this.idCampeonato = idCampeonato;
        this.idGame = idGame;
        this.nome = nome;
        this.localCampeonato = localCampeonato;
        this.idEquipes = idEquipes;
        this.premiacao = premio;
    }

    public int getPremiacao() {
        return premiacao;
    }

    public void setPremiacao(int premiacao) {
        this.premiacao = premiacao;
    }

    public String getIdCampeonato() {
        return idCampeonato;
    }

    public void setIdCampeonato(String idCampeonato) {
        this.idCampeonato = idCampeonato;
    }

    public String getIdGame() {
        return idGame;
    }

    public void setIdGame(String idGame) {
        this.idGame = idGame;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLocalCampeonato() {
        return localCampeonato;
    }

    public void setLocalCampeonato(String localCampeonato) {
        this.localCampeonato = localCampeonato;
    }

    public List<String> getIdEquipes() {
        return idEquipes;
    }

    public void setIdEquipes(List<String> idEquipes) {
        this.idEquipes = idEquipes;
    }

}
