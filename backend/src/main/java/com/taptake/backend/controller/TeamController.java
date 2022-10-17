package com.taptake.backend.controller;

import com.taptake.backend.DTO.TeamDTO;
import com.taptake.backend.model.Game;
import com.taptake.backend.model.Organization;
import com.taptake.backend.model.Team;
import com.taptake.backend.service.GameService;
import com.taptake.backend.service.OrganizationService;
import com.taptake.backend.service.TeamService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/team")
public class TeamController {

    @Autowired
    private TeamService ts;

    @Autowired
    private OrganizationService os;

    @Autowired
    private GameService gs;

    @PostMapping
    public ResponseEntity<?> save(@RequestBody TeamDTO teamDTO){
        Optional<Game> optionalGame = gs.findById(UUID.fromString(teamDTO.getIdJogo()));
        Optional<Organization> optionalOrganization = os.findById(UUID.fromString(teamDTO.getIdOrg()));
        if(!optionalGame.isPresent() || !optionalOrganization.isPresent()){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        List<Team> lt = ts.findAllByNomeTime(teamDTO.getNomeTime());
        for(Team t : lt){
            if(t.getGame().getIdJogo().equals(teamDTO.getIdJogo()) && t.getOrg().equals(teamDTO.getIdOrg()) && t.getNomeTime().equals(teamDTO.getNomeTime())){
                return ResponseEntity.status(HttpStatus.CONFLICT).build();
            }
        }
        var team = new Team();
        team.setGame(optionalGame.get());
        team.setOrg(optionalOrganization.get());
        team.setNomeTime(teamDTO.getNomeTime());
        return ResponseEntity.status(HttpStatus.CREATED).body(ts.save(team));
    }
    @DeleteMapping
    public ResponseEntity<?> deleteOne(@RequestParam String id){
        ts.deleteOne(UUID.fromString(id));
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    @GetMapping
    public ResponseEntity<?> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(ts.findAll());
    }

    @GetMapping("/id/org")
    public ResponseEntity<?> findByOrg(@RequestParam String idOrg){
        Optional<Organization> optionalOrganization= os.findById(UUID.fromString(idOrg));
        if(!optionalOrganization.isPresent()){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(ts.findAllByIdOrg(UUID.fromString(idOrg)));
    }
    @GetMapping("/id")
    public ResponseEntity<?> findById(@RequestParam String id){
        Optional<Team> optionalTeam = ts.findById(UUID.fromString(id));
        if(!optionalTeam.isPresent()){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(optionalTeam.get());
    }

    @GetMapping("/name")
    public ResponseEntity<?> findByNomeTime(@RequestParam String nomeTime){
        return ResponseEntity.status(HttpStatus.OK).body(ts.findAllByNomeTime(nomeTime));
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody TeamDTO teamDTO, @RequestParam String id){
        Optional<Game> optionalGame = gs.findById(UUID.fromString(teamDTO.getIdJogo()));
        Optional<Organization> optionalOrganization = os.findById(UUID.fromString(teamDTO.getIdOrg()));
        Optional<Team> optionalTeam = ts.findById(UUID.fromString(id));
        if(!optionalGame.isPresent() || !optionalOrganization.isPresent() || !optionalTeam.isPresent()){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        Team savedTeam = optionalTeam.get();
        if(!teamDTO.getIdJogo().equals(savedTeam.getGame().getIdJogo())){
            savedTeam.setGame(optionalGame.get());
        }
        if(!teamDTO.getIdOrg().equals(savedTeam.getOrg().getIdOrg())){
            savedTeam.setOrg(optionalOrganization.get());
        }
        if(!teamDTO.getNomeTime().equals(savedTeam.getNomeTime())){
            savedTeam.setNomeTime(teamDTO.getNomeTime());
        }
        return ResponseEntity.status(HttpStatus.OK).body(ts.update(savedTeam));

    }

}
