package com.taptake.backend.DRO;

public class UserDRO {
    private String idUsuario;
    private String nome;
    private String email;
    private String role;

    public UserDRO(String idUsuario, String nome, String email, String role) {
        this.idUsuario = idUsuario;
        this.nome = nome;
        this.email = email;
        this.role = role;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}
