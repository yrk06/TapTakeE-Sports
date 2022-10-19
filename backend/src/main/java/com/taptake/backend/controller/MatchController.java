package com.taptake.backend.controller;

import com.taptake.backend.DTO.MatchDTO;
import com.taptake.backend.model.Championship;
import com.taptake.backend.model.Match;
import com.taptake.backend.model.MatchParticipation;
import com.taptake.backend.model.Team;
import com.taptake.backend.service.ChampionshipService;
import com.taptake.backend.service.MatchService;
import com.taptake.backend.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/match")
public class MatchController {

    @Autowired
    private MatchService matchService;

    @Autowired
    private ChampionshipService cs;
    @Autowired
    private TeamService ts;

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody MatchDTO matchDTO) {
        Optional<Championship> optionalChampionship = cs.findById(UUID.fromString(matchDTO.getIdCampeonato()));
        if (!optionalChampionship.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        var match = new Match();
        match.setChampionship(optionalChampionship.get());
        Set<Team> teamList = new HashSet<>();

        for (String t : matchDTO.getIdEquipes()) {
            Optional<Team> team = ts.findById(UUID.fromString(t));
            if (!team.isPresent()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
            teamList.add(team.get());

        }
        match.setEquipes(teamList);
        return ResponseEntity.status(HttpStatus.CREATED).body(matchService.save(match));
    }

    @GetMapping("/id")
    public ResponseEntity<Object> findById(@RequestParam String id) {
        Optional<Match> optionalMatch = matchService.findById(UUID.fromString(id));
        if (!optionalMatch.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(optionalMatch.get());
    }

    @GetMapping("/champ")
    public ResponseEntity<Object> findByChampionship(@RequestParam String id) {
        Optional<Championship> optionalC = cs.findById(UUID.fromString(id));
        if (!optionalC.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(matchService.findByChampionship(optionalC.get()));
    }

    @DeleteMapping
    public ResponseEntity<Object> deleteOne(@RequestParam String id) {
        matchService.delete(UUID.fromString(id));
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping
    public ResponseEntity<Object> update(@RequestBody MatchDTO matchDTO, @RequestParam String id) {
        Optional<Match> optionalMatch = matchService.findById(UUID.fromString(id));
        if (!optionalMatch.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        Optional<Championship> optionalC = cs.findById(UUID.fromString(matchDTO.getIdCampeonato()));
        if (!optionalC.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        Match m = optionalMatch.get();
        if (!optionalMatch.get().getChampionship().getIdCampeonato().toString().equals(matchDTO.getIdCampeonato())) {
            m.setChampionship(optionalC.get());
        }
        return ResponseEntity.status(HttpStatus.OK).body(matchService.update(m));
    }

}
