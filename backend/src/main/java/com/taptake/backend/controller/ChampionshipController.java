package com.taptake.backend.controller;

import com.taptake.backend.DRO.ChampionshipDRO;
import com.taptake.backend.DTO.ChampionshipDTO;
import com.taptake.backend.model.Championship;
import com.taptake.backend.model.ChampionshipParticipation;
import com.taptake.backend.model.Game;
import com.taptake.backend.model.Team;
import com.taptake.backend.service.ChampionshipParticipationService;
import com.taptake.backend.service.ChampionshipService;
import com.taptake.backend.service.GameService;
import com.taptake.backend.service.TeamService;

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
        for (String id : cDTO.getIdEquipes()) {
            Optional<Team> opt = ts.findById(UUID.fromString(id));
            if (opt.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
            ChampionshipParticipation cp = new ChampionshipParticipation();
            cp.setChampionship(c);
            cp.setTeam(opt.get());
            cp.setPosicao(-1);

            scp.add(cps.save(cp));

        }
        c.setParticipacoes(scp);
        return ResponseEntity.status(HttpStatus.CREATED).body(cs.save(c).generateDRO());
    }

    @GetMapping("/id")
    public ResponseEntity<Object> findById(@RequestParam String id) {
        Optional<Championship> optC = cs.findById(UUID.fromString(id));
        if (optC.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(optC.get().generateDRO());
    }

    @GetMapping("/name")
    public ResponseEntity<Object> findAllByNome(@RequestParam String nome) {
        List<Championship> lst = cs.findAllByNome(nome);
        List<ChampionshipDRO> lstDRO = new ArrayList<>();
        for (Championship c : lst) {
            lstDRO.add(c.generateDRO());
        }
        return ResponseEntity.status(HttpStatus.OK).body(lstDRO);
    }

    @GetMapping
    public ResponseEntity<Object> findAllByNome() {
        List<Championship> champs = cs.findAll();
        List<ChampionshipDRO> response = new LinkedList<>();
        for (Championship c : champs) {
            response.add(c.generateDRO());
        }
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping
    public ResponseEntity<Object> delete(@RequestParam String id) {
        Optional<Championship> optChamp = cs.findById(UUID.fromString(id));
        if (optChamp.isPresent()) {
            Championship champ = optChamp.get();
            for (ChampionshipParticipation cp : champ.getParticipacoes()) {
                cps.deleteOne(cp.getidParticipacaoCampeonato());
            }
        }
        cs.deleteOne(UUID.fromString(id));
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping
    public ResponseEntity<Object> update(@RequestBody ChampionshipDTO championshipDTO, @RequestParam String id) {

        Optional<Championship> optChampionship = cs.findById(UUID.fromString(id));
        if (optChampionship.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Optional<Game> optGame = gs.findById(UUID.fromString(championshipDTO.getIdJogo()));
        if (optGame.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        Championship championship = optChampionship.get();

        Set<ChampionshipParticipation> updatedChampionshipParticipation = new HashSet<>();

        for (String idEquipe : championshipDTO.getIdEquipes()) {

            Optional<Team> optTeam = ts.findById(UUID.fromString(idEquipe));
            if (optTeam.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }

            Optional<ChampionshipParticipation> existingParticipation = championship.getParticipacoes().stream()
                    .filter((
                            ChampionshipParticipation champP) -> Objects
                                    .equals(champP.getTeam().getIdEquipe().toString(), idEquipe))
                    .findFirst();

            if (existingParticipation.isPresent()) {

                updatedChampionshipParticipation.add(existingParticipation.get());

            } else {

                ChampionshipParticipation cp = new ChampionshipParticipation();
                cp.setChampionship(championship);
                cp.setTeam(optTeam.get());
                cp.setPosicao(-1);
                updatedChampionshipParticipation.add(cps.save(cp));

            }

        }

        for (ChampionshipParticipation champp : championship.getParticipacoes()) {
            if (!updatedChampionshipParticipation.contains(champp)) {
                cps.deleteOne(champp.getidParticipacaoCampeonato());
            }
        }
        championship.setParticipacoes(updatedChampionshipParticipation);

        if (!championship.getGame().getIdJogo().toString().equals(championshipDTO.getIdJogo())) {
            championship.setGame(optGame.get());
        }
        if (!championship.getNome().equals(championshipDTO.getNome())) {
            championship.setNome(championshipDTO.getNome());
        }
        if (championship.getPremiacao() != championshipDTO.getPremiacao()) {
            championship.setPremiacao(championshipDTO.getPremiacao());
        }
        if (!championship.getLocalCampeonato().equals(championshipDTO.getLocalCampeonato())) {
            championship.setLocalCampeonato(championshipDTO.getLocalCampeonato());
        }
        return ResponseEntity.status(HttpStatus.OK).body(cs.update(championship).generateDRO());
    }

    @PostMapping("/rank")
    public ResponseEntity<Object> setPos(@RequestParam int pos, @RequestParam String idPart) {
        Optional<ChampionshipParticipation> opt = cps.findById(UUID.fromString(idPart));
        if (opt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        opt.get().setPosicao(pos);
        cps.save(opt.get());
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
