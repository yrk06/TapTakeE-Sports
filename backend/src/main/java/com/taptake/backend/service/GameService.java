package com.taptake.backend.service;


import com.taptake.backend.model.Game;
import com.taptake.backend.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    @Transactional
    public Game save(Game game){
        return gameRepository.save(game);
    }
    public Optional<Game> findByNome(String nome){
        return gameRepository.findByNome(nome);
    }

    public Optional<Game> findById(UUID id) {
        return gameRepository.findById(id);
    }

    public Game update(Game game) { return gameRepository.save(game); }

    public void deleteOne(Game game) { gameRepository.delete(game); }
}
