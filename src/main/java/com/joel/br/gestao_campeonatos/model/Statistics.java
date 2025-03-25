package com.joel.br.gestao_campeonatos.model;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "statistics")
@Data
public class Statistics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User player;

    @ManyToOne
    @JoinColumn(name = "tournament", nullable = false)
    private Tournament tournament;

    private int goals = 0;
    private int assists = 0;
    private int matchesPlayed = 0;

    private int yellowCards = 0;
    private int redCards = 0;

    private Integer seasonYear;

    // Estatísticas específicas para goleiros
    private int cleanSheets = 0;
    private int savesMade = 0;

    // Estatísticas avançadas
    private int minutesPlayed = 0;
    private int passesCompleted = 0;
    private int tackles = 0;


    public double getGoalsPerMatch() {
        if (matchesPlayed == 0) return 0;
        return (double) goals / matchesPlayed;
    }

    public double getAssistsPerMatch() {
        if (matchesPlayed == 0) return 0;
        return (double) assists / matchesPlayed;
    }

    public double getCardsPerMatch() {
        if (matchesPlayed == 0) return 0;
        return (double) (yellowCards + redCards) / matchesPlayed;
    }

    // Método para incrementar estatísticas
    public void incrementGoals(int amount) {
        this.goals += amount;
    }

    public void incrementAssists(int amount) {
        this.assists += amount;
    }

    public void incrementYellowCards(int amount) {
        this.yellowCards += amount;
    }

    public void incrementRedCards(int amount) {
        this.redCards += amount;
    }

    public void incrementMatchesPlayed() {
        this.matchesPlayed++;
    }
}
