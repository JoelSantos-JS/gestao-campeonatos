package com.joel.br.gestao_campeonatos.repository;

import com.joel.br.gestao_campeonatos.enums.MatchStatus;
import com.joel.br.gestao_campeonatos.model.Match;
import com.joel.br.gestao_campeonatos.model.Team;
import com.joel.br.gestao_campeonatos.model.Tournament;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;
import java.util.List;

public interface MatchRepository extends JpaRepository<Match, Long> {

    List<Match> findByTournament(Tournament tournament);
    List<Match> findByHomeTeamOrAwayTeam(Team team1,Team team2);
    List<Match> findByDateTimeBetween(Instant start, Instant end);
    List<Match> findByStatus(MatchStatus status);
    List<Match> findUpcomingMatches(Instant now);
}
