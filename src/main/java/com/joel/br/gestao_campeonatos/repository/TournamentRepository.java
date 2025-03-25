package com.joel.br.gestao_campeonatos.repository;

import com.joel.br.gestao_campeonatos.enums.TournamentStatus;
import com.joel.br.gestao_campeonatos.model.Tournament;
import com.joel.br.gestao_campeonatos.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

public interface TournamentRepository extends JpaRepository<Tournament, Long> {

    List<Tournament> findByStatus(TournamentStatus status);

    List<Tournament> findByUser(User user);

    List<Tournament> findByStartDateAfterAndEndDateBefore(Instant start , Instant end);


}
