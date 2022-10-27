package com.taptake.backend.DTO;

import java.util.Date;

public class PlayerUserTeamDTO {

    private String idEquipeUsuario;
    private String idJogador;
    private Date dataEntrada;
    private Date dataSaida;

    public String getIdEquipeUsuario() {
        return idEquipeUsuario;
    }

    public void setIdEquipeUsuario(String idEquipeUsuario) {
        this.idEquipeUsuario = idEquipeUsuario;
    }

    public String getIdJogador() {
        return idJogador;
    }

    public void setIdJogador(String idJogador) {
        this.idJogador = idJogador;
    }

    public Date getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(Date dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public Date getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(Date dataSaida) {
        this.dataSaida = dataSaida;
    }
}
