package com.joel.br.gestao_campeonatos.repository;

import com.joel.br.gestao_campeonatos.model.Match;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchRepository extends JpaRepository<Match, Long> {
}
