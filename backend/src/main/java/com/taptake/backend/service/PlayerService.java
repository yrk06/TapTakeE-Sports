package com.taptake.backend.service;

import com.taptake.backend.model.Player;
import com.taptake.backend.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    @Transactional
    public Player save(Player player){
        return playerRepository.save(player);
    }

    public List<Player> getAll() {
        return playerRepository.findAll();
    }
}
