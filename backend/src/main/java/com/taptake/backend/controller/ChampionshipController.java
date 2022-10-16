package com.taptake.backend.controller;

import com.taptake.backend.DTO.ChampionshipDTO;
import com.taptake.backend.model.Championship;
import com.taptake.backend.model.Game;
import com.taptake.backend.service.ChampionshipService;
import com.taptake.backend.service.GameService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/champ")
public class ChampionshipController {

    @Autowired
    private ChampionshipService cs;

    @Autowired
    private GameService gs;

    @PostMapping
    public ResponseEntity<?> save(@RequestBody ChampionshipDTO cDTO){

        List<Championship> cList = cs.findAllByNome(cDTO.getNome());
        Optional<Game> og = gs.findById(UUID.fromString(cDTO.getIdJogo()));
        if(!og.isPresent()){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        for (Championship c : cList){
            if((c.getGame().getIdJogo().equals(UUID.fromString(cDTO.getIdJogo())) && c.getNome().equals(cDTO.getNome()) && c.getPremiacao()==cDTO.getPremiacao() && c.getLocalCampeonato().equals(cDTO.getLocalCampeonato()))){
                return ResponseEntity.status(HttpStatus.CONFLICT).build();
            }
        }
        var cNew = new Championship();
        cNew.setGame(og.get());
        cNew.setNome(cDTO.getNome());
        cNew.setPremiacao(cDTO.getPremiacao());
        cNew.setLocalCampeonato(cDTO.getLocalCampeonato());
        return ResponseEntity.status(HttpStatus.CREATED).body(cs.save(cNew));
    }
    @GetMapping("/id")
    public ResponseEntity<?> findById(@RequestParam String id){
        Optional<Championship> optC = cs.findById(UUID.fromString(id));
        if(!optC.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(optC.get());
    }
    @GetMapping("/name")
    public ResponseEntity<?> findAllByNome(@RequestParam String nome){
        return ResponseEntity.status(HttpStatus.OK).body(cs.findAllByNome(nome));
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestParam String id){
        cs.deleteOne(UUID.fromString(id));
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    @PutMapping
    public ResponseEntity<?> update(@RequestBody ChampionshipDTO c, @RequestParam String id){
        Optional<Championship> oc =  cs.findById(UUID.fromString(id));
        Optional<Game> og = gs.findById(UUID.fromString(c.getIdJogo()));

        if(!oc.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        Championship savedC = oc.get();
        if(!og.isPresent()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        if(!savedC.getGame().getIdJogo().equals(c.getIdJogo())){
            savedC.setGame(og.get());
        }
        if(!savedC.getNome().equals(c.getNome())){
            savedC.setNome(c.getNome());
        }
        if(savedC.getPremiacao()!=c.getPremiacao()){
            savedC.setPremiacao(c.getPremiacao());
        }
        if(!savedC.getLocalCampeonato().equals(c.getLocalCampeonato())){
            savedC.setLocalCampeonato(c.getLocalCampeonato());
        }
        return ResponseEntity.status(HttpStatus.OK).body(cs.update(savedC));
    }

}
