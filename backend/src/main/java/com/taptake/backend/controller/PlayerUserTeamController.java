package com.taptake.backend.controller;


import com.taptake.backend.DTO.PlayerUserTeamDTO;
import com.taptake.backend.DTO.UserTeamDTO;
import com.taptake.backend.model.Player;
import com.taptake.backend.model.PlayerUserTeam;
import com.taptake.backend.model.UserTeam;
import com.taptake.backend.service.PlayerService;
import com.taptake.backend.service.PlayerUserTeamService;
import com.taptake.backend.service.UserTeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/playeruserteam")
public class PlayerUserTeamController {

    @Autowired
    private PlayerUserTeamService playerUserTeamService;

    @Autowired
    private PlayerService playerService;

    @Autowired
    private UserTeamService userTeamService;


    @PostMapping
    public ResponseEntity<Object> save(@RequestBody PlayerUserTeamDTO playerUserTeamDTO) {
        Optional<UserTeam> optionalUserTeam = userTeamService.findById(UUID.fromString(playerUserTeamDTO.getIdEquipeUsuario()));
        if (optionalUserTeam.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        Optional<Player> optionalPlayer = playerService.findById(UUID.fromString(playerUserTeamDTO.getIdJogador()));
        if (optionalPlayer.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        List<PlayerUserTeam> listPLayer = playerUserTeamService.findByUserteam(userTeamService.findById(UUID.fromString(playerUserTeamDTO.getIdEquipeUsuario())).get());
        for (PlayerUserTeam p : listPLayer) {
            if (playerUserTeamDTO.getIdJogador().equals(p.getPlayer().getIdJogador().toString())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
        }

        var playerUserteam = new PlayerUserTeam();
        playerUserteam.setPlayer(optionalPlayer.get());
        playerUserteam.setUserteam(optionalUserTeam.get());
        playerUserteam.setDataEntrada(playerUserTeamDTO.getDataEntrada());
        playerUserteam.setDataSaida(playerUserTeamDTO.getDataSaida());

        return ResponseEntity.status(HttpStatus.CREATED).body(playerUserTeamService.save(playerUserteam));

    }

    @GetMapping("/userteam")
    public ResponseEntity<Object> findByUserteam(@RequestParam String id) {
        Optional<UserTeam> userTeam = userTeamService.findById(UUID.fromString(id));

        if (userTeam.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(playerUserTeamService.findByUserteam(userTeam.get()));

    }

    @DeleteMapping
    public ResponseEntity<Object> delete(@RequestParam String id) {
        playerUserTeamService.delete(UUID.fromString(id));
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PutMapping
    public ResponseEntity<Object> update(@RequestBody PlayerUserTeamDTO playerUserTeamDTO, @RequestParam String id) {
        Optional<PlayerUserTeam> optionalPlayerUserTeam = playerUserTeamService.findById(UUID.fromString(id));
        if (optionalPlayerUserTeam.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Optional<UserTeam> optionalUserTeam = userTeamService.findById(UUID.fromString(playerUserTeamDTO.getIdEquipeUsuario()));
        if (optionalUserTeam.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        Optional<Player> optionalPlayer = playerService.findById(UUID.fromString(playerUserTeamDTO.getIdJogador()));
        if (optionalPlayer.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        List<PlayerUserTeam> listPLayer = playerUserTeamService.findByUserteam(userTeamService.findById(UUID.fromString(playerUserTeamDTO.getIdEquipeUsuario())).get());
        for (PlayerUserTeam p : listPLayer) {
            if (playerUserTeamDTO.getIdJogador().equals(p.getPlayer().getIdJogador().toString())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
        }

        PlayerUserTeam updatePlayerUserTeam = optionalPlayerUserTeam.get();

        if (!updatePlayerUserTeam.getPlayer().getIdJogador().equals(optionalPlayer.get().getIdJogador())){
            updatePlayerUserTeam.setPlayer(optionalPlayer.get());
        }

        if (!updatePlayerUserTeam.getUserteam().getIdEquipeUsuario().equals(optionalUserTeam.get().getIdEquipeUsuario())){
            updatePlayerUserTeam.setUserteam(optionalUserTeam.get());
        }

        if (!updatePlayerUserTeam.getDataEntrada().equals(playerUserTeamDTO.getDataEntrada())) {
            updatePlayerUserTeam.setDataEntrada(playerUserTeamDTO.getDataEntrada());
        }

        if (!updatePlayerUserTeam.getDataSaida().equals(playerUserTeamDTO.getDataEntrada())) {
            updatePlayerUserTeam.setDataSaida(playerUserTeamDTO.getDataSaida());
        }

        return ResponseEntity.status(HttpStatus.OK).body(playerUserTeamService.update(updatePlayerUserTeam));

    }

}