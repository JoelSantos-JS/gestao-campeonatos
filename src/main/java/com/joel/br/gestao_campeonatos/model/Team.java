package com.joel.br.gestao_campeonatos.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "teams")
@Data
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Team name is required")
    private String name;

    // Changing to ManyToMany because a team can participate in multiple tournaments
    @ManyToMany(mappedBy = "teams")
    private Set<Tournament> tournaments = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "team_players",
            joinColumns = @JoinColumn(name = "team_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    @Size(min = 5, max = 15, message = "Team must have between 5 and 15 players")
    private Set<User> players = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "captain_id", nullable = false)
    private User captain;

    // Team statistics
    private int wins = 0;
    private int draws = 0;
    private int losses = 0;
    private int goalsScored = 0;
    private int goalsConceded = 0;

    // Relationships with matches
    @OneToMany(mappedBy = "homeTeam")
    private Set<Match> homeMatches = new HashSet<>();

    @OneToMany(mappedBy = "awayTeam")
    private Set<Match> awayMatches = new HashSet<>();

    // Useful methods
    public int getPoints() {
        return (wins * 3) + draws;
    }

    public int getGoalDifference() {
        return goalsScored - goalsConceded;
    }

    public int getMatchesPlayed() {
        return wins + draws + losses;
    }
}