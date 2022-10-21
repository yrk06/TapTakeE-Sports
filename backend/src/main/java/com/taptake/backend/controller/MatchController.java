package com.taptake.backend.controller;

import com.taptake.backend.DRO.MatchDRO;
import com.taptake.backend.DTO.MatchDTO;
import com.taptake.backend.model.*;
import com.taptake.backend.service.ChampionshipService;
import com.taptake.backend.service.MatchPerformanceService;
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

    @Autowired
    private MatchPerformanceService mps;

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody MatchDTO matchDTO) {
        Optional<Championship> optionalChampionship = cs.findById(UUID.fromString(matchDTO.getIdCampeonato()));
        if (!optionalChampionship.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        var match = new Match();
        match.setChampionship(optionalChampionship.get());
        Set<Team> teamList = new HashSet<>();
        match.setData(matchDTO.getData());
        for (String t : matchDTO.getIdEquipes()) {
            Optional<Team> team = ts.findById(UUID.fromString(t));
            if (!team.isPresent()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
            teamList.add(team.get());

        }
        match.setEquipes(teamList);
        match = matchService.save(match);

        for (Team t : match.getEquipes()){
            for(Player p : t.getPlayers()){
                MatchPerformance mp = new MatchPerformance();
                mp.setMatch(match);
                mp.setPlayer(p);
                mp.setPontuacao(0);
                mps.save(mp);
            }
        }





        return ResponseEntity.status(HttpStatus.CREATED).body(match.generateDRO());



    }

    @GetMapping("/id")
    public ResponseEntity<Object> findById(@RequestParam String id) {
        Optional<Match> match = matchService.findById(UUID.fromString(id));
        if (!match.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        MatchDRO matchDRO = match.get().generateDRO();
        return ResponseEntity.status(HttpStatus.OK).body(matchDRO);

    }

    @GetMapping("/champ")
    public ResponseEntity<Object> findByChampionship(@RequestParam String id) {
        Optional<Championship> optionalC = cs.findById(UUID.fromString(id));
        if (!optionalC.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        List<Match> matchList = matchService.findByChampionship(optionalC.get());
        List<MatchDRO> matchDROlist = new ArrayList<>();
        for (Match match : matchList) {
            matchDROlist.add(match.generateDRO());
        }
        return ResponseEntity.status(HttpStatus.OK).body(matchDROlist);
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
        if(!m.getData().equals(matchDTO.getData())){
            m.setData(matchDTO.getData());
        }
        Set<Team> teamSet = m.getEquipes();
        for(String idTeam : matchDTO.getIdEquipes()){
            Optional<Team> t = ts.findById(UUID.fromString(idTeam));
            if(!t.isPresent()){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
            if(teamSet.contains(t.get())){
                teamSet.add(t.get());
            }
        }
        m.setEquipes(teamSet);
        return ResponseEntity.status(HttpStatus.OK).body(matchService.update(m).generateDRO());
    }
//NOVO METODO PARA RETORNAR PERFORMANCE POR PLAYER
}
