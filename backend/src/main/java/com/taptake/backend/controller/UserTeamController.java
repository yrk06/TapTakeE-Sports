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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    ResponseEntity<Object> save(@RequestBody UserTeamDTO userTeamDTO){
        Optional<User> optionalUser = userService.findById(UUID.fromString(userTeamDTO.getIdUsuario()));
        if (!optionalUser.isPresent()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        Optional<Game> optionalGame = gameService.findById(UUID.fromString(userTeamDTO.getIdJogo()));
        if (!optionalGame.isPresent()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        var userTeam = new UserTeam();
        userTeam.setUser(optionalUser.get());
        userTeam.setGame(optionalGame.get());

        userTeam = userTeamService.save(userTeam);

        return ResponseEntity.status(HttpStatus.CREATED).body(userTeamService.save(userTeam));



    }


}
