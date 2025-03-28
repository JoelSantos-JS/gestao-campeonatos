package com.joel.br.gestao_campeonatos.service;


import com.joel.br.gestao_campeonatos.enums.MatchStatus;
import com.joel.br.gestao_campeonatos.expections.EntityNotFoundException;
import com.joel.br.gestao_campeonatos.model.Match;
import com.joel.br.gestao_campeonatos.model.Team;
import com.joel.br.gestao_campeonatos.model.Tournament;
import com.joel.br.gestao_campeonatos.repository.MatchRepository;
import com.joel.br.gestao_campeonatos.repository.TournamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class MatchService {


    private final MatchRepository matchRepository;
    private final TournamentRepository tournamentRepository;

    @Autowired
    public MatchService(MatchRepository matchRepository, TournamentRepository tournamentRepository) {
        this.matchRepository = matchRepository;
        this.tournamentRepository = tournamentRepository;
    }


    public List<Match> findAllMatchs() {
        return matchRepository.findAll();
    }

     public Match findMatchById(Long id) {
        return matchRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Match not found"));
     }

    public List<Match> findAllByStatus(){
        return matchRepository.findByStatus(MatchStatus.IN_PROGRESS);
    }

    public List<Match> findAllMatchByDate(Instant start, Instant end) {
        return  matchRepository.findByDateTimeBetween(start, end);
    }

    public List<Match> findMatchByTeams(Team team1, Team team2) {
        return  matchRepository.findByHomeTeamOrAwayTeam(team1, team2);
    }

    public List<Match> findMatchByTournament(Tournament tournament) {
        return  matchRepository.findByTournament(tournament);
    }

    public List<Match> findMatchByDate(Instant date) {
        return  matchRepository.findUpcomingMatches(date);
    }
}
