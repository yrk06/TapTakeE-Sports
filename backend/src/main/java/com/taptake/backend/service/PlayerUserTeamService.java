package com.taptake.backend.service;

import com.taptake.backend.model.PlayerUserTeam;
import com.taptake.backend.model.UserTeam;
import com.taptake.backend.repository.PlayerUserTeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.TransactionScoped;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PlayerUserTeamService {

    @Autowired
    private PlayerUserTeamRepository playerUserTeamRepository;

    @TransactionScoped
    public PlayerUserTeam save(PlayerUserTeam playerUserTeamm) {
        return playerUserTeamRepository.save(playerUserTeamm);
    }


    public void delete(UUID id) {
        playerUserTeamRepository.deleteById(id);
    }

    public List<PlayerUserTeam> findByUserteam(UserTeam Userteam) {
        return playerUserTeamRepository.findByUserteam(Userteam);
    }


    public Optional<PlayerUserTeam> findById(UUID id) {
        return playerUserTeamRepository.findById(id);
    }

    public PlayerUserTeam update(PlayerUserTeam playerUserTeam) {
        return playerUserTeamRepository.save(playerUserTeam);
    }
}
