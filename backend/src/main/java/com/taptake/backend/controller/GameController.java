package com.taptake.backend.controller;


import com.taptake.backend.DTO.GameDTO;
import com.taptake.backend.model.Game;
import com.taptake.backend.service.GameService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/game")
public class GameController {

    @Autowired
    private GameService gameService;

    @PostMapping
    public ResponseEntity<?> save(@RequestBody GameDTO gameDTO){
        Optional<Game> optionalGame = gameService.findByNome(gameDTO.getNome());
        if(optionalGame.isPresent() && gameDTO.getNome().equals(optionalGame.get().getNome()) && gameDTO.getTipoJogo().equals(optionalGame.get().getTipoJogo()) && gameDTO.getQuantidadeJogadores()==optionalGame.get().getQuantidadeJogadores()){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        var game = new Game();
        BeanUtils.copyProperties(gameDTO, game);
        return ResponseEntity.status(HttpStatus.CREATED).body(gameService.save(game));
    }

    @GetMapping
    public ResponseEntity<?> findById(@RequestParam String id){

        Optional<Game> game = gameService.findById(UUID.fromString(id));
        if (game.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(game.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody GameDTO gameDTO, @RequestParam String id){
        Optional<Game> optionalGame = gameService.findById(UUID.fromString(id));

        if(!optionalGame.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        Game game = optionalGame.get();
        if(!game.getNome().equals(gameDTO.getNome())){
            game.setNome(gameDTO.getNome());
        }
        if(!game.getTipoJogo().equals(gameDTO.getTipoJogo())){
            game.setTipoJogo(gameDTO.getTipoJogo());
        }
        if(game.getQuantidadeJogadores()!=gameDTO.getQuantidadeJogadores()){
            game.setQuantidadeJogadores(gameDTO.getQuantidadeJogadores());
        }
        return ResponseEntity.status(HttpStatus.OK).body(gameService.update(game));
    }
    @DeleteMapping
    public ResponseEntity<?> deleteOne(@RequestParam String id){
        Optional<Game> optionalGame = gameService.findById(UUID.fromString(id));
        if(optionalGame.isPresent()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        gameService.deleteOne(UUID.fromString(id));
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
