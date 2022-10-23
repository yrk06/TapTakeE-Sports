package com.taptake.backend.controller;

import com.taptake.backend.DTO.UserTeamDTO;
import com.taptake.backend.model.Game;
import com.taptake.backend.model.User;

import com.taptake.backend.model.UserTeam;
import com.taptake.backend.service.GameService;
import com.taptake.backend.service.UserService;
import com.taptake.backend.service.UserTeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/userteam")
public class UserTeamController {

    @Autowired
    private UserTeamService userTeamService;

    @Autowired
    private UserService userService;

    @Autowired
    private GameService gameService;


    @PostMapping
    public ResponseEntity<Object> save(@RequestBody UserTeamDTO userTeamDTO) {
        Optional<User> optionalUser = userService.findById(UUID.fromString(userTeamDTO.getIdUsuario()));
        if (optionalUser.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        Optional<Game> optionalGame = gameService.findById(UUID.fromString(userTeamDTO.getIdJogo()));
        if (optionalGame.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        var userTeam = new UserTeam();
        userTeam.setUser(optionalUser.get());
        userTeam.setGame(optionalGame.get());

        userTeam = userTeamService.save(userTeam);

        return ResponseEntity.status(HttpStatus.CREATED).body(userTeamService.save(userTeam));

    }

    @GetMapping("/id")
    public ResponseEntity<Object> findById(@RequestParam String id) {
        Optional<UserTeam> userTeam = userTeamService.findById(UUID.fromString(id));

        if (userTeam.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(userTeam.get());

    }

    @DeleteMapping
    public ResponseEntity<Object> delete(@RequestParam String id) {
        userService.deleteOne(UUID.fromString(id));
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

//    @PutMapping
//    public ResponseEntity<Object> update(@RequestBody UserTeamDTO userTeamDTO, @RequestParam String id) {
//        Optional<UserTeam> optional_UserTeam = userTeamService.findById(UUID.fromString(id));
//
//        if (optional_UserTeam.isEmpty()) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//        }
//
//        Optional<User> optional_User = userService.findById(UUID.fromString(userTeamDTO.getIdUsuario()));
//        if (optional_User.isEmpty()) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//        }
//
//        Optional<Game> optional_Game = gameService.findById(UUID.fromString(userTeamDTO.getIdJogo()));
//        if (optional_Game.isEmpty()) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//        }
//
//        UserTeam updateUserTeam = optional_UserTeam.get();
//
//        if (!updateUserTeam.getUser().getIdUsuario().equals(optional_User.get().getIdUsuario())){
//            updateUserTeam.setUser(optional_User.get());
//        }
//
//        if (!updateUserTeam.getGame().getIdJogo().equals(optional_Game.get().getIdJogo())){
//            updateUserTeam.setGame(optional_Game.get());
//        }
//
//        if (!optional_UserTeam.get().getUser().getIdUsuario().toString().equals(userTeamDTO.getIdUsuario())) {
//            updateUserTeam.setUser(optional_User.get());
//        }
//        if (!optional_UserTeam.get().getGame().getIdJogo().toString().equals(userTeamDTO.getIdJogo())) {
//            updateUserTeam.setGame(optional_Game.get());
//        }
//
//        return ResponseEntity.status(HttpStatus.OK).body(userTeamService.update(updateUserTeam));
//
//    }

}
