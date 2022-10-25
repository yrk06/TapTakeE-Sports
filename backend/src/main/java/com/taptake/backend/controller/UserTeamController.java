package com.taptake.backend.controller;

import com.taptake.backend.DTO.UserTeamDTO;
import com.taptake.backend.model.Game;
import com.taptake.backend.model.Player;
import com.taptake.backend.model.User;

import com.taptake.backend.model.UserTeam;
import com.taptake.backend.service.GameService;
import com.taptake.backend.service.UserService;
import com.taptake.backend.service.UserTeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;
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

        // Get user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if ((authentication instanceof AnonymousAuthenticationToken)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        String currentUserName = authentication.getName();

        // If user is authenticated, the service must find it
        User user = userService.findByEmail(currentUserName).get();

        Optional<Game> optionalGame = gameService.findById(UUID.fromString(userTeamDTO.getIdJogo()));
        if (optionalGame.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        UserTeam userTeam = new UserTeam();
        userTeam.setUser(user);
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

    @PutMapping
    public ResponseEntity<Object> update(@RequestBody UserTeamDTO userTeamDTO,
            @RequestParam String id) {

        Optional<UserTeam> optional_UserTeam = userTeamService.findById(UUID.fromString(id));

        if (optional_UserTeam.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        UserTeam updateUserTeam = optional_UserTeam.get();

        if (!updateUserTeam.getGame().getIdJogo().toString().equals(userTeamDTO.getIdJogo())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        if (!updateUserTeam.getUser().getIdUsuario().toString().equals(userTeamDTO.getIdJogo())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        if (userTeamDTO.getPlayers().size() > updateUserTeam.getGame().getQuantidadeJogadores()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(userTeamService.update(updateUserTeam));

    }

}
