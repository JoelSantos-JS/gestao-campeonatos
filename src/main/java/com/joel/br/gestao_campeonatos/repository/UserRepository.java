package com.joel.br.gestao_campeonatos.repository;

import com.joel.br.gestao_campeonatos.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByEmail(String email);
    Optional<User> findByLawyerCode(String code);
    boolean existsByEmail(String email);
    boolean existsByLawyerCode(String code);
}
