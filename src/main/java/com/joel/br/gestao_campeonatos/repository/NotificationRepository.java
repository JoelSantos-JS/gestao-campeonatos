package com.joel.br.gestao_campeonatos.repository;

import com.joel.br.gestao_campeonatos.model.Notification;
import com.joel.br.gestao_campeonatos.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification,Long> {

    List<Notification> findByUser(User user);
    List<Notification> findByUserAndRead(User user ,boolean read);
    List<Long> countUnreadByUser(User user);

}
