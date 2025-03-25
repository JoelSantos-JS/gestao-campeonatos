package com.joel.br.gestao_campeonatos.model;


import com.joel.br.gestao_campeonatos.enums.NotificationType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.Instant;

@Entity
@Table(name = "notifications")
@Data
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @NotNull(message = "Message is required")
    @Column(columnDefinition = "TEXT")
    private String message;

    @NotNull(message = "Notification date is required")
    private Instant dateTime;

    private boolean read;
    @Enumerated(EnumType.STRING)
    private NotificationType type;
}
