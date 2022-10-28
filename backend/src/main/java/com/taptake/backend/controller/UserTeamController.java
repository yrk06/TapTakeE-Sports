package com.taptake.backend.controller;

import com.taptake.backend.DRO.UserTeamDRO;
import com.taptake.backend.DTO.UserTeamDTO;
import com.taptake.backend.model.Game;
import com.taptake.backend.model.MatchPerformance;
import com.taptake.backend.model.Player;
import com.taptake.backend.model.PlayerUserTeam;
import com.taptake.backend.model.User;

import com.taptake.backend.model.UserTeam;
import com.taptake.backend.service.GameService;
import com.taptake.backend.service.MatchPerformanceService;
import com.taptake.backend.service.PlayerService;
import com.taptake.backend.service.PlayerUserTeamService;
import com.taptake.backend.service.UserService;
import com.taptake.backend.service.UserTeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashSet;
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
    private PlayerService playerService;

    @Autowired
    private PlayerUserTeamService playerUserTeamService;

    @Autowired
    private MatchPerformanceService matchPerformanceService;

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

        // A user cannot have multiple teams for the same game
        List<UserTeam> userTeams = userTeamService.findByUser(user);
        for (UserTeam team : userTeams) {
            if (team.getGame() == optionalGame.get()) {
                return ResponseEntity.status(HttpStatus.CONFLICT).build();
            }
        }

        UserTeam userTeam = new UserTeam();
        userTeam.setUser(user);
        userTeam.setGame(optionalGame.get());
        userTeam.setPlayers(new HashSet<>());

        userTeam = userTeamService.save(userTeam);

        return ResponseEntity.status(HttpStatus.CREATED).body(userTeamService.save(userTeam));

    }

    @GetMapping("/")
    public ResponseEntity<Object> findAll() {

        List<UserTeamDRO> userTeamDROs = new LinkedList<>();

        for (UserTeam userTeam : userTeamService.findAll()) {
            UserTeamDRO dro = userTeam.generateUserTeamDRO();
            int acc_points = 0;
            for (PlayerUserTeam player : userTeam.getPlayers()) {

                for (MatchPerformance mp : matchPerformanceService.findByPeriodAndPlayer(player.getDataEntrada(),
                        player.getDataSaida() != null ? player.getDataSaida() : new Date(), player.getPlayer())) {
                    acc_points += mp.getPontuacao();
                }
            }
            dro.setPoints(acc_points);
            userTeamDROs.add(dro);
        }

        return ResponseEntity.status(HttpStatus.OK).body(userTeamDROs);
    }

    @GetMapping("/id")
    public ResponseEntity<Object> findById(@RequestParam String id) {
        Optional<UserTeam> userTeamOpt = userTeamService.findById(UUID.fromString(id));

        if (userTeamOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        UserTeam userTeam = userTeamOpt.get();

        UserTeamDRO dro = userTeam.generateUserTeamDRO();
        int acc_points = 0;
        for (PlayerUserTeam player : userTeam.getPlayers()) {

            for (MatchPerformance mp : matchPerformanceService.findByPeriodAndPlayer(player.getDataEntrada(),
                    player.getDataSaida() != null ? player.getDataSaida() : new Date(), player.getPlayer())) {
                acc_points += mp.getPontuacao();
            }
        }
        dro.setPoints(acc_points);

        return ResponseEntity.status(HttpStatus.OK).body(dro);

    }

    @DeleteMapping
    public ResponseEntity<Object> delete(@RequestParam String id) {

        Optional<UserTeam> optional_UserTeam = userTeamService.findById(UUID.fromString(id));

        if (optional_UserTeam.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        UserTeam updateUserTeam = optional_UserTeam.get();

        // Get user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if ((authentication instanceof AnonymousAuthenticationToken)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        String currentUserName = authentication.getName();

        // If user is authenticated, the service must find it
        User user = userService.findByEmail(currentUserName).get();

        if (updateUserTeam.getUser() != user) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

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

        // Get user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if ((authentication instanceof AnonymousAuthenticationToken)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        String currentUserName = authentication.getName();

        // If user is authenticated, the service must find it
        User user = userService.findByEmail(currentUserName).get();

        if (updateUserTeam.getUser() != user) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        // Game must be the same
        if (!updateUserTeam.getGame().getIdJogo().toString().equals(userTeamDTO.getIdJogo())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        if (userTeamDTO.getPlayers().size() > updateUserTeam.getGame().getQuantidadeJogadores()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        for (String playerid : userTeamDTO.getPlayers()) {
            Optional<Player> optPlayer = playerService.findById(UUID.fromString(playerid));

            if (!optPlayer.isPresent() || optPlayer.get().getTeam().getGame() != updateUserTeam.getGame()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
            boolean skip = false;
            for (PlayerUserTeam pteam : updateUserTeam.getPlayers()) {
                if (pteam.getPlayer() == optPlayer.get() && pteam.getDataSaida() == null) {
                    skip = true;
                    break;
                }
            }
            if (skip)
                break;
            PlayerUserTeam newPlayer = new PlayerUserTeam();
            newPlayer.setDataEntrada(new Date());
            newPlayer.setDataSaida(null);
            newPlayer.setPlayer(optPlayer.get());
            newPlayer.setUserteam(updateUserTeam);

            newPlayer = playerUserTeamService.save(newPlayer);
            updateUserTeam.getPlayers().add(newPlayer);
        }

        for (Player p : updateUserTeam.getActivePlayers()) {
            if (!userTeamDTO.getPlayers().contains(p.getIdJogador().toString())) {

                for (PlayerUserTeam pteam : updateUserTeam.getPlayers()) {
                    if (pteam.getPlayer() == p && pteam.getDataSaida() == null) {
                        pteam.setDataSaida(new Date());

                        playerUserTeamService.update(pteam);
                    }
                }
            }
        }

        return ResponseEntity.status(HttpStatus.OK).body(userTeamService.update(updateUserTeam).generateUserTeamDRO());

    }

}
