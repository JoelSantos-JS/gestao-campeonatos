package com.joel.br.gestao_campeonatos.model;


import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;

@Entity
@Table
@Data
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private User user;


    private String message;

    private Instant dateTime;

    private boolean read;

}
