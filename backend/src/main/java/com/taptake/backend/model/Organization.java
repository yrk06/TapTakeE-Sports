package com.taptake.backend.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "Organizacao")

public class Organization implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Type(type = "uuid-char")
    private UUID idOrg;

    @Column(nullable = false)
    private String nomeOrg;

    @Column(nullable = false)
    private String localOrg;

    @Column(nullable = false)
    private Date dataCriacao;

    public UUID getIdOrg() {
        return idOrg;
    }

    public void setIdOrg(UUID idOrg) {
        this.idOrg = idOrg;
    }

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
