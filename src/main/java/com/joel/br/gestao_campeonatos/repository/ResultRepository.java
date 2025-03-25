package com.joel.br.gestao_campeonatos.repository;

import com.joel.br.gestao_campeonatos.model.Match;
import com.joel.br.gestao_campeonatos.model.Result;
import com.joel.br.gestao_campeonatos.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Repository
public interface ResultRepository extends JpaRepository<Result, Long> {

    Optional<Result> findByMatch(Match match);

    List<Result> findByRegisteredBy(User user);

    List<Result> findByRegistrationDateBetween(Instant start, Instant end);

    boolean existsByMatch(Match match);
}