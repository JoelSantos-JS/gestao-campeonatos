package com.joel.br.gestao_campeonatos.model;

import com.joel.br.gestao_campeonatos.enums.MatchStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.Instant;

@Entity
@Table(name = "results")
@Data
public class Result {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @OneToOne
    @JoinColumn(name = "match_id", nullable = false)
    private Match match;

    @Min(value = 0, message = "Goals cannot be negative")
    private Integer homeTeamGoals;
    @Min(value = 0 , message = "Goals cannot be negative")
    private Integer awayTeamGoals;


    @NotNull(message = "registered date is required")
    private Instant registrationDate;

    @ManyToOne
    @JoinColumn(name = "registered_by_id", nullable = false)
    private User registerBy;

    @Column(columnDefinition = "TEXT")
    private String note;




    // Estes métodos pertencem à classe Result
    public Team getWinner() {
        if (homeTeamGoals > awayTeamGoals) {
            return match.getHomeTeam();
        } else if (awayTeamGoals > homeTeamGoals) {
            return match.getAwayTeam();
        } else {
            return null; // Empate
        }
    }

    public boolean isDraw() {
        return homeTeamGoals == awayTeamGoals;
    }

    public String getResultMessage() {
        if (homeTeamGoals > awayTeamGoals) {
            return match.getHomeTeam().getName() + " won";
        } else if (awayTeamGoals > homeTeamGoals) {
            return match.getAwayTeam().getName() + " won";
        } else {
            return "Match ended in a draw";
        }
    }


}
