package com.joel.br.gestao_campeonatos.repository;

import com.joel.br.gestao_campeonatos.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team,Long> {
}
