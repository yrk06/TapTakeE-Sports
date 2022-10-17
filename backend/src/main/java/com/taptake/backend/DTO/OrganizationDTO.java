package com.taptake.backend.DTO;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class OrganizationDTO {

    private String nomeOrg;
    private String localOrg;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date dataCriacao;

    public String getNomeOrg() {
        return nomeOrg;
    }

    public void setNomeOrg(String nomeOrg) {
        this.nomeOrg = nomeOrg;
    }

    public String getLocalOrg() {
        return localOrg;
    }

    public void setLocalOrg(String localOrg) {
        this.localOrg = localOrg;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
}
