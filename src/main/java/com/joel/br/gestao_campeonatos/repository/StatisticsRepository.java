package com.joel.br.gestao_campeonatos.repository;

import com.joel.br.gestao_campeonatos.model.Statistics;
import com.joel.br.gestao_campeonatos.model.Tournament;
import com.joel.br.gestao_campeonatos.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StatisticsRepository extends JpaRepository<Statistics, Long> {

    Optional<Statistics> findByPlayerAndTournament(User player, Tournament tournament);

    List<Statistics> findByPlayer(User player);

    List<Statistics> findByTournament(Tournament tournament);

    List<Statistics> findByTournamentAndSeasonYear(Tournament tournament, Integer seasonYear);

    @Query("SELECT s FROM Statistics s WHERE s.tournament.id = :tournamentId ORDER BY s.goals DESC")
    List<Statistics> findTopScorersByTournament(Long tournamentId);

    @Query("SELECT s FROM Statistics s WHERE s.tournament.id = :tournamentId ORDER BY s.assists DESC")
    List<Statistics> findTopAssistsByTournament(Long tournamentId);

    @Query("SELECT s FROM Statistics s WHERE s.tournament IS NULL ORDER BY s.goals DESC")
    List<Statistics> findCareerTopScorers();
}