package com.taptake.backend.model;

import javax.persistence.*;

import com.taptake.backend.DRO.LeftMatchPerformanceDRO;

@Entity
@Table(name = "PerformancePartida")
public class MatchPerformance {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idPerformancePartida;

    @ManyToOne
    @JoinColumn(name = "idPartida")
    private Match match;

    @ManyToOne
    @JoinColumn(name = "idJogador")
    private Player player;

    @Column(nullable = false)
    private int pontuacao;

    public int getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public LeftMatchPerformanceDRO generateLeftDRO() {
        LeftMatchPerformanceDRO dro = new LeftMatchPerformanceDRO();

        dro.setScore(pontuacao);
        dro.setPlayerId(this.player.getIdJogador().toString());

        return dro;
    }

    public int getIdPerformancePartida() {
        return idPerformancePartida;
    }

    public void setIdPerformancePartida(int idPerformancePartida) {
        this.idPerformancePartida = idPerformancePartida;
    }

}
