package com.joel.br.gestao_campeonatos.repository;

import com.joel.br.gestao_campeonatos.model.Ranking;
import com.joel.br.gestao_campeonatos.model.Team;
import com.joel.br.gestao_campeonatos.model.Tournament;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RankingRepository extends JpaRepository<Ranking, Long> {

    List<Ranking> findByTournamentOrderByPointsDescGoalDifferenceDescGoalsScoredDesc(Tournament tournament);

    Optional<Ranking> findByTournamentAndTeam(Tournament tournament, Team team);

    @Query("SELECT r FROM Ranking r WHERE r.tournament.id = :tournamentId ORDER BY r.points DESC, r.goalsScored - r.goalsConceded DESC, r.goalsScored DESC")
    List<Ranking> findTournamentRankingById(Long tournamentId);

    void deleteByTournament(Tournament tournament);
}