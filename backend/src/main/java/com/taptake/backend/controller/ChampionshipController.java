package com.taptake.backend.controller;

import com.taptake.backend.DTO.ChampionshipDTO;
import com.taptake.backend.model.Championship;
import com.taptake.backend.model.ChampionshipParticipation;
import com.taptake.backend.model.Game;
import com.taptake.backend.model.Team;
import com.taptake.backend.service.ChampionshipParticipationService;
import com.taptake.backend.service.ChampionshipService;
import com.taptake.backend.service.GameService;
import com.taptake.backend.service.TeamService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/champ")
public class ChampionshipController {

    @Autowired
    private ChampionshipService cs;

    @Autowired
    private GameService gs;

    @Autowired
    private TeamService ts;

    @Autowired
    private ChampionshipParticipationService cps;

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody ChampionshipDTO cDTO) {

        List<Championship> cList = cs.findAllByNome(cDTO.getNome());
        Optional<Game> og = gs.findById(UUID.fromString(cDTO.getIdJogo()));
        if (og.isEmpty()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        for (Championship c : cList) {
            if ((c.getGame().getIdJogo().equals(UUID.fromString(cDTO.getIdJogo())) && c.getNome().equals(cDTO.getNome())
                    && c.getPremiacao() == cDTO.getPremiacao()
                    && c.getLocalCampeonato().equals(cDTO.getLocalCampeonato()))) {
                return ResponseEntity.status(HttpStatus.CONFLICT).build();
            }
        }
        Set<ChampionshipParticipation> scp = new HashSet<>();


        var cNew = new Championship();
        cNew.setGame(og.get());
        cNew.setNome(cDTO.getNome());
        cNew.setPremiacao(cDTO.getPremiacao());
        cNew.setLocalCampeonato(cDTO.getLocalCampeonato());
        Championship c = cs.save(cNew);
        for(String id : cDTO.getIdEquipes()){
            Optional<Team> opt = ts.findById(UUID.fromString(id));
            if(opt.isEmpty()){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
            ChampionshipParticipation cp = new ChampionshipParticipation();
            cp.setChampionship(c);
            cp.setTeam(opt.get());
            cp.setPosicao(-1);

            scp.add(cps.save(cp));

        }
        c.setParticipacoes(scp);
        return ResponseEntity.status(HttpStatus.CREATED).body(cs.save(c));
    }

    @GetMapping("/id")
    public ResponseEntity<Object> findById(@RequestParam String id) {
        Optional<Championship> optC = cs.findById(UUID.fromString(id));
        if (optC.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(optC.get());
    }

    @GetMapping("/name")
    public ResponseEntity<Object> findAllByNome(@RequestParam String nome) {
        return ResponseEntity.status(HttpStatus.OK).body(cs.findAllByNome(nome));
    }

    @GetMapping
    public ResponseEntity<Object> findAllByNome() {
        return ResponseEntity.status(HttpStatus.OK).body(cs.findAll());
    }

    @DeleteMapping
    public ResponseEntity<Object> delete(@RequestParam String id) {
        cs.deleteOne(UUID.fromString(id));
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping
    public ResponseEntity<Object> update(@RequestBody ChampionshipDTO c, @RequestParam String id) {
        Optional<Championship> oc = cs.findById(UUID.fromString(id));
        Optional<Game> og = gs.findById(UUID.fromString(c.getIdJogo()));
        Set<ChampionshipParticipation> scp = new HashSet<>();
        if (oc.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        Championship savedC = oc.get();
        if (og.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        for(String idequipes : c.getIdEquipes()){
            Optional<Team> opt = ts.findById(UUID.fromString(idequipes));
            if(opt.isEmpty()){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
            ChampionshipParticipation cp = new ChampionshipParticipation();
            cp.setChampionship(oc.get());
            cp.setTeam(opt.get());
            cp.setPosicao(-1);

            scp.add(cps.save(cp));

        }
        if (!savedC.getGame().getIdJogo().equals(c.getIdJogo())) {
            savedC.setGame(og.get());
        }
        if (!savedC.getNome().equals(c.getNome())) {
            savedC.setNome(c.getNome());
        }
        if (savedC.getPremiacao() != c.getPremiacao()) {
            savedC.setPremiacao(c.getPremiacao());
        }
        if (!savedC.getLocalCampeonato().equals(c.getLocalCampeonato())) {
            savedC.setLocalCampeonato(c.getLocalCampeonato());
        }
        savedC.setParticipacoes(scp);
        return ResponseEntity.status(HttpStatus.OK).body(cs.update(savedC));
    }

    @PostMapping("/rank")
    public ResponseEntity<Object> setPos(@RequestParam int pos, @RequestParam String idPart){
        Optional<ChampionshipParticipation> opt = cps.findById(UUID.fromString(idPart));
        if(opt.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        opt.get().setPosicao(pos);
        cps.save(opt.get());
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
