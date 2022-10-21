package com.taptake.backend.controller;

import com.taptake.backend.DTO.PlayerDTO;
import com.taptake.backend.model.Player;
import com.taptake.backend.model.Team;
import com.taptake.backend.service.PlayerService;
import com.taptake.backend.service.TeamService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/player")
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @Autowired
    private TeamService ts;

    @GetMapping
    public ResponseEntity<?> findById(@RequestParam String id) {
        Optional<Player> player = playerService.findById(UUID.fromString(id));
        if (player.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(player.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody PlayerDTO playerDTO) {
        Optional<Player> playerOptional = playerService.findByNome(playerDTO.getNome());
        if (playerOptional.isPresent() && playerOptional.get().getCargo() == playerDTO.getCargo()
                && playerOptional.get().getTeam().getIdEquipe().toString().equals(playerDTO.getIdEquipe())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        Optional<Team> team = ts.findById(UUID.fromString(playerDTO.getIdEquipe()));
        if(!team.isPresent()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        var player = new Player();
        player.setTeam(team.get());
        player.setCargo(playerDTO.getCargo());
        player.setNome(playerDTO.getNome());
        return ResponseEntity.status(HttpStatus.CREATED).body(playerService.save(player));
    }

    @DeleteMapping
    public ResponseEntity<?> deleteOne(@RequestParam("id") String id) {
        Optional<Player> player = playerService.findById(UUID.fromString(id));
        if(!player.isPresent()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        playerService.deleteOne(player.get().getIdJogador());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody PlayerDTO playerDTO, @RequestParam String id) {
        Optional<Player> userOptional = playerService.findById(UUID.fromString(id));

        if (!userOptional.isPresent()) {
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
        if(!ot.isPresent()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        if(!userOptional.get().getTeam().getIdEquipe().toString().equals(playerDTO.getIdEquipe())){
            newPlayer.setTeam(ot.get());
        }
        return ResponseEntity.status(HttpStatus.OK).body(playerService.update(newPlayer));
    }

}
