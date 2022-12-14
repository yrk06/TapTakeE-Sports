package com.taptake.backend.controller;

import com.taptake.backend.DRO.TeamDRO;
import com.taptake.backend.DTO.TeamDTO;
import com.taptake.backend.model.Game;
import com.taptake.backend.model.Organization;
import com.taptake.backend.model.Player;
import com.taptake.backend.model.Team;
import com.taptake.backend.service.GameService;
import com.taptake.backend.service.OrganizationService;
import com.taptake.backend.service.PlayerService;
import com.taptake.backend.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/team")
public class TeamController {

    @Autowired
    private TeamService ts;

    @Autowired
    private OrganizationService os;

    @Autowired
    private PlayerService ps;

    @Autowired
    private GameService gs;

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody TeamDTO teamDTO){
        Optional<Game> optionalGame = gs.findById(UUID.fromString(teamDTO.getIdJogo()));
        Optional<Organization> optionalOrganization = os.findById(UUID.fromString(teamDTO.getIdOrg()));
        if(optionalGame.isEmpty() || optionalOrganization.isEmpty()){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        List<Team> lt = ts.findAllByNomeTime(teamDTO.getNomeTime());
        for(Team t : lt){
            if(t.getGame().getIdJogo().toString().equals(teamDTO.getIdJogo()) && t.getOrg().getIdOrg().toString().equals(teamDTO.getIdOrg()) && t.getNomeTime().equals(teamDTO.getNomeTime())){
                return ResponseEntity.status(HttpStatus.CONFLICT).build();
            }
        }
        Set<Player> players = new HashSet<>();
        for(String idPlayer : teamDTO.getIdJogadores()){
            Optional<Player> opt = ps.findById(UUID.fromString(idPlayer));
            if(opt.isEmpty()){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
            players.add(opt.get());
        }
        var team = new Team();
        team.setGame(optionalGame.get());
        team.setOrg(optionalOrganization.get());
        team.setNomeTime(teamDTO.getNomeTime());
        team.setPlayers(players);
        return ResponseEntity.status(HttpStatus.CREATED).body(ts.save(team).generateDRO());
    }
    @DeleteMapping
    public ResponseEntity<Object> deleteOne(@RequestParam String id){
        ts.deleteOne(UUID.fromString(id));
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    @GetMapping
    public ResponseEntity<Object> getAll(){
        List<Team> lt = ts.findAll();
        List<TeamDRO> ltdro = new ArrayList<>();
        for(Team t : lt){
            ltdro.add(t.generateDRO());
        }
        return ResponseEntity.status(HttpStatus.OK).body(ltdro);

    }

    @GetMapping("/id/org")
    public ResponseEntity<Object> findByOrg(@RequestParam String idOrg){
        Optional<Organization> optionalOrganization= os.findById(UUID.fromString(idOrg));
        if(optionalOrganization.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        List<Team> lt = ts.findAllByOrg(optionalOrganization.get());
        List<TeamDRO> ltdro = new ArrayList<>();
        for(Team t : lt){
            ltdro.add(t.generateDRO());
        }
        return ResponseEntity.status(HttpStatus.OK).body(ltdro);
    }
    @GetMapping("/id/jogo")
    public ResponseEntity<Object> getAllByGame(@RequestParam String idJogo){
        Optional<Game> game = gs.findById(UUID.fromString(idJogo));
        if(game.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        List<Team> lt = ts.findAllByGame(game.get());
        List<TeamDRO> ltdro = new ArrayList<>();
        for(Team t : lt){
            ltdro.add(t.generateDRO());
        }
        return ResponseEntity.status(HttpStatus.OK).body(ltdro);

    }
    @GetMapping("/id")
    public ResponseEntity<Object> findById(@RequestParam String id){
        Optional<Team> optionalTeam = ts.findById(UUID.fromString(id));
        if(optionalTeam.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(optionalTeam.get().generateDRO());
    }

    @GetMapping("/name")
    public ResponseEntity<Object> findAllByNomeTime(@RequestParam String nomeTime){
        List<Team> lt = ts.findAllByNomeTime(nomeTime);
        List<TeamDRO> ltdro = new ArrayList<>();
        for(Team t : lt){
            ltdro.add(t.generateDRO());
        }
        return ResponseEntity.status(HttpStatus.OK).body(ltdro);
    }

    @PutMapping
    public ResponseEntity<Object> update(@RequestBody TeamDTO teamDTO, @RequestParam String id){
        Optional<Game> optionalGame = gs.findById(UUID.fromString(teamDTO.getIdJogo()));
        Optional<Organization> optionalOrganization = os.findById(UUID.fromString(teamDTO.getIdOrg()));
        Optional<Team> optionalTeam = ts.findById(UUID.fromString(id));
        if (optionalTeam.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        if(optionalGame.isEmpty() || optionalOrganization.isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        Team savedTeam = optionalTeam.get();
        if(!teamDTO.getIdJogo().equals(savedTeam.getGame().getIdJogo().toString())){
            savedTeam.setGame(optionalGame.get());
        }
        if(!teamDTO.getIdOrg().equals(savedTeam.getOrg().getIdOrg().toString())){
            savedTeam.setOrg(optionalOrganization.get());
        }
        if(!teamDTO.getNomeTime().equals(savedTeam.getNomeTime())){
            savedTeam.setNomeTime(teamDTO.getNomeTime());
        }
        Set<Player> newSet = new HashSet<>();
        for(String s : teamDTO.getIdJogadores()){
            Optional<Player> opt = ps.findById(UUID.fromString(s));
            if(!opt.isPresent()){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
            newSet.add(opt.get());
        }
        return ResponseEntity.status(HttpStatus.OK).body(ts.update(savedTeam).generateDRO());
    }

}
