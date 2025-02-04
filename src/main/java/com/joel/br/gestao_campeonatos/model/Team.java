package com.joel.br.gestao_campeonatos.model;


import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table
@Data
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false)
    private String name;


    @ManyToOne
    @JoinColumn(name = "tournament_id", nullable = false)
    private Tournament tournament;


    @ManyToMany
    @JoinTable(name = "team_players" , joinColumns = @JoinColumn(name = "team_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> players;

    @ManyToOne
    @JoinColumn(name = "captain_id", nullable = false)
    private User captain;
}
