package com.joel.br.gestao_campeonatos.dto;

import lombok.Data;

// Como uma classe DTO (não uma entidade)
@Data
public class RankingDTO {
    private Long tournamentId;
    private Long teamId;
    private String teamName;
    private int position;
    private int matchesPlayed;
    private int wins;
    private int draws;
    private int losses;
    private int goalsScored;
    private int goalsConceded;
    private int points;
    private int goalDifference;

    // Construtores, getters e setters
}