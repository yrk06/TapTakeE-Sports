package com.taptake.backend.DTO;

public class ChampionshipDTO {

    private String idJogo;
    private String nome;
    private String localCampeonato;
    private int premiacao;

    public String getIdJogo() {
        return idJogo;
    }

    public void setIdJogo(String idJogo) {
        this.idJogo = idJogo;
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

    public int getPremiacao() {
        return premiacao;
    }

    public void setPremiacao(int premiacao) {
        this.premiacao = premiacao;
    }
}
