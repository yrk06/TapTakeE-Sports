package com.taptake.backend.DTO;

public class UserTeamDTO {

    private String idUsuario;
    private String idJogo;
    private int pontos;


    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getIdJogo() {
        return idJogo;
    }

    public void setIdJogo(String idJogo) {
        this.idJogo = idJogo;
    }

}
