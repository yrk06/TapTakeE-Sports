package com.taptake.backend.service;

import com.taptake.backend.model.Game;
import com.taptake.backend.model.Player;
import com.taptake.backend.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    @Transactional
    public Player save(Player player) {
        return playerRepository.save(player);
    }

    public Optional<Player> findById(UUID id) {
        return playerRepository.findById(id);
    }

    public List<Player> getAll() {
        return playerRepository.findAll();
    }

    public Optional<Player> findByNome(String nome) {
        return playerRepository.findByNome(nome);
    }

    public void deleteOne(UUID idJogador) {
        playerRepository.deleteById(idJogador);
    }

    public Player update(Player player) {
        return playerRepository.save(player);
    }

    public List<Player> findByGame(Game game) {
        return playerRepository.findByGame(game);
    }
}
