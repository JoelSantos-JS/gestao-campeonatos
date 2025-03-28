package com.joel.br.gestao_campeonatos.service;


import com.joel.br.gestao_campeonatos.enums.TournamentStatus;
import com.joel.br.gestao_campeonatos.expections.EntityNotFoundException;
import com.joel.br.gestao_campeonatos.model.Tournament;
import com.joel.br.gestao_campeonatos.model.User;
import com.joel.br.gestao_campeonatos.repository.TournamentRepository;
import com.joel.br.gestao_campeonatos.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Service
public class TournamentService {

    private final TournamentRepository tournamentRepository;
    private final UserRepository userRepository;
    @Autowired
    public TournamentService(TournamentRepository tournamentRepository, UserRepository userRepository) {
        this.tournamentRepository = tournamentRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public List<Tournament> listAllTournaments() {
        return tournamentRepository.findAll();
    }


    public Tournament findById(Long id) {
        return  tournamentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Tournament not found"));
    }

    @Transactional
    public Tournament createTournament(Tournament tournament) {
        boolean user = userRepository.existsByEmail(tournament.getAdmin().getEmail());
        if(!user) {
            throw  new EntityNotFoundException("User not exists");
        }

        if (tournament.getStartDate().isAfter(tournament.getEndDate())) {
            throw new IllegalArgumentException("Start date must be before end date");
        }


        return  tournamentRepository.save(tournament);
    }


    public List<Tournament> getTournamentByStatus() {
        return tournamentRepository.findByStatus(TournamentStatus.IN_PROGRESS);
    }

    public List<Tournament> getTournamentByAdmin(User admin) {
        return  tournamentRepository.findByUser(admin);
    }

    public List<Tournament> findTournamentByDate(Instant start, Instant end){
        return  tournamentRepository.findByStartDateAfterAndEndDateBefore(start, end);

    }
    @Transactional
    public Tournament updateTournament(Tournament tournament, Long id) {
        Tournament tournament1 = findById(id);


        tournament1.setName(tournament.getName());
        tournament1.setStartDate(tournament.getStartDate());
        tournament1.setEndDate(tournament.getStartDate());



        if(tournament.getStartDate().isAfter(tournament1.getEndDate())) {
            throw new RuntimeException(" Start date must be before end date ");
        }

        tournament1.setStatus(tournament.getStatus());
        tournament1.setRules(tournament.getRules());

        if(tournament.getAdmin() != null && !tournament.getAdmin().equals(tournament1.getAdmin())){
            boolean admin = userRepository.existsByEmail(tournament.getAdmin().getEmail());

            if(!admin) {
                throw new EntityNotFoundException("User not exists");
            }

            tournament1.setAdmin(tournament.getAdmin() );
        }

        return tournamentRepository.save(tournament1);
    }

    @Transactional
    public void deleteTournament(Long id) {
        Tournament tournament = findById(id);

        tournamentRepository.delete(tournament);
    }
}
