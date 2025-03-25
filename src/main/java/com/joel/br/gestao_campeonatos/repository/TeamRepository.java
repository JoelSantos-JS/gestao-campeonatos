package com.joel.br.gestao_campeonatos.repository;

import com.joel.br.gestao_campeonatos.model.Team;
import com.joel.br.gestao_campeonatos.model.Tournament;
import com.joel.br.gestao_campeonatos.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeamRepository extends JpaRepository<Team,Long> {

    List<Team> findByCaptain(User captain);
    List<Team> findByPlayersContaining(User player);
    List<Team> findByTournamentsContaining (Tournament tournament);
    List<Team> findTopTeamsByWins(int limit);
}
