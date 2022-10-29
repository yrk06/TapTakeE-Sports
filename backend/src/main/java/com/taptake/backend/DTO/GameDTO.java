package com.taptake.backend.DTO;

public class GameDTO {

    private String nome;
    private String tipoJogo;
    private int quantidadeJogadores;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipoJogo() {
        return tipoJogo;
    }

    public void setTipoJogo(String tipoJogo) {
        this.tipoJogo = tipoJogo;
    }

    public void setQuantidadeJogadores(int quantidadeJogadores) {
        this.quantidadeJogadores = quantidadeJogadores;
    }

    public int getQuantidadeJogadores() {
        return quantidadeJogadores;
    }

}
