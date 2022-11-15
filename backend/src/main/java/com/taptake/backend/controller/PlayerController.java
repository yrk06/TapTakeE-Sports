package com.taptake.backend.controller;

import com.taptake.backend.DRO.PlayerDRO;
import com.taptake.backend.DTO.PlayerDTO;
import com.taptake.backend.model.Game;
import com.taptake.backend.model.Player;
import com.taptake.backend.model.Team;
import com.taptake.backend.service.GameService;
import com.taptake.backend.service.PlayerService;
import com.taptake.backend.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/player")
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @Autowired
    private TeamService ts;

    @Autowired
    private GameService gameService;

    @GetMapping
    public ResponseEntity<Object> findById(@RequestParam String id) {
        Optional<Player> player = playerService.findById(UUID.fromString(id));
        if (player.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(player.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody PlayerDTO playerDTO) {
        Optional<Player> playerOptional = playerService.findByNome(playerDTO.getNome());
        if (playerOptional.isPresent() && playerOptional.get().getCargo().equals(playerDTO.getCargo())
                && playerOptional.get().getTeam().getIdEquipe().toString().equals(playerDTO.getIdEquipe())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        Optional<Team> team = ts.findById(UUID.fromString(playerDTO.getIdEquipe()));
        if (team.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        var player = new Player();
        player.setTeam(team.get());
        player.setCargo(playerDTO.getCargo());
        player.setNome(playerDTO.getNome());
        player.setMatchPerformances(new HashSet<>());

        return ResponseEntity.status(HttpStatus.CREATED).body(playerService.save(player));
    }

    @DeleteMapping
    public ResponseEntity<Object> deleteOne(@RequestParam("id") String id) {
        Optional<Player> player = playerService.findById(UUID.fromString(id));
        if (player.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        playerService.deleteOne(player.get().getIdJogador());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

    @GetMapping("/game")
    public ResponseEntity<Object> findAllByGame(@RequestParam String gameId) {
        Optional<Game> game = gameService.findById(UUID.fromString(gameId));
        if (!game.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        List<Player> players = playerService.findByGame(game.get());
        List<PlayerDRO> playerDROs = new LinkedList<>();
        for (Player p : players) {
            playerDROs.add(p.generateDRO());
        }
        return ResponseEntity.status(HttpStatus.OK).body(playerDROs);
    }

    @PutMapping
    public ResponseEntity<Object> update(@RequestBody PlayerDTO playerDTO, @RequestParam String id) {
        Optional<Player> userOptional = playerService.findById(UUID.fromString(id));

        if (userOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        Player newPlayer = userOptional.get();

        if (!playerDTO.getNome().equals(newPlayer.getNome())) {
            newPlayer.setNome(playerDTO.getNome());
        }
        if (!playerDTO.getCargo().equals(newPlayer.getCargo())) {
            newPlayer.setCargo(playerDTO.getCargo());
        }
        Optional<Team> ot = ts.findById(UUID.fromString(playerDTO.getIdEquipe()));
        if (ot.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        if (!userOptional.get().getTeam().getIdEquipe().toString().equals(playerDTO.getIdEquipe())) {
            newPlayer.setTeam(ot.get());
        }
        return ResponseEntity.status(HttpStatus.OK).body(playerService.update(newPlayer));
    }

}
