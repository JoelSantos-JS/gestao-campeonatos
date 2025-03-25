package com.joel.br.gestao_campeonatos.model;


import com.joel.br.gestao_campeonatos.enums.MatchStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.Instant;

@Entity
@Data
@Table(name = "matches")
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "tournament_id", nullable = false)
    private Tournament tournament;

    @ManyToOne
    @JoinColumn(name = "home_team_id", nullable = false) // Time de Casa;
    private Team homeTeam;

    @ManyToOne
    @JoinColumn(name = "away_team_id", nullable = false) //Time de Fora
    private Team awayTeam;

    @NotNull(message = "Match Time and team is required")
    private Instant dateTime;

    private String location;
    @Enumerated(EnumType.STRING)
    private MatchStatus status = MatchStatus.SCHEDULED;

    private Integer homeTeamGoals;
    private Integer awayTeamGoals;

    private String referee; // Juiz;





    public Team getWinner() {
        if(status != MatchStatus.FINISHED || homeTeamGoals == null || awayTeamGoals == null) {
            return  null;
        }

        if (homeTeamGoals > awayTeamGoals) {
            return homeTeam;
        } else if (awayTeamGoals > homeTeamGoals) {
            return awayTeam;
        } else {
            return null; // Empate
        }
    }



    public  boolean isDraw() {
        // Só podemos determinar um empate se a partida estiver finalizada
        if (status != MatchStatus.FINISHED || homeTeamGoals == null || awayTeamGoals == null) {
            return false;
        }

        return homeTeamGoals.equals(awayTeamGoals);
    }


    public String getResultMessage() {
        if (status != MatchStatus.FINISHED) {
            return "Match not finished yet";
        }

        if (homeTeamGoals > awayTeamGoals) {
            return homeTeam.getName() + " won";
        } else if (awayTeamGoals > homeTeamGoals) {
            return awayTeam.getName() + " won";
        } else {
            return "Match ended in a draw";
        }
    }

}
