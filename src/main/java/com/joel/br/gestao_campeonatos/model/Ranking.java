package com.joel.br.gestao_campeonatos.model;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "ranking")
@Data
public class Ranking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int position;


    @ManyToOne
    @JoinColumn(name = "tournament_id", nullable = false)
    private Tournament tournament;


    @ManyToOne
    @JoinColumn(name = "team_id", nullable = false)
    private Team team;

    private int matchesPlayed;

    private int wins;

    private int draws;

    private int loses;

    private int goalsScored;
    private int goalsConceded;

    private int goalDifference;

    private int points;




    public int getGoalDifference(){

        return  goalsScored - goalsConceded;
    }


    public void calculatePoints() {
        this.points= (wins * 3) + draws;
    }


    public void  updateFromTeam() {
        this.matchesPlayed = wins + draws + loses;
    }

}
