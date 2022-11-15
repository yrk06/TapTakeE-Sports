package com.taptake.backend.repository;

import com.taptake.backend.model.Game;
import com.taptake.backend.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PlayerRepository extends JpaRepository<Player, UUID> {
    Optional<Player> findByNome(String nome);

    @Query("SELECT p FROM Player p JOIN p.team t WHERE t.game = ?1")
    List<Player> findByGame(Game game);
}
