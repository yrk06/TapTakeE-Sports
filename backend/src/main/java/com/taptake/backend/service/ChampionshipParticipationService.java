package com.taptake.backend.service;

import com.taptake.backend.model.Championship;
import com.taptake.backend.model.ChampionshipParticipation;
import com.taptake.backend.model.Team;
import com.taptake.backend.repository.ChampionshipParticipationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ChampionshipParticipationService {

    @Autowired
    private ChampionshipParticipationRepository cpr;

    @Transactional
    public ChampionshipParticipation save(ChampionshipParticipation cp){
        return cpr.save(cp);
    }

    public List<ChampionshipParticipation> findByChampionship(Championship championship){
        return cpr.findByChampionship(championship);
    }
    public List<ChampionshipParticipation> findAll(){
        return cpr.findAll();
    }
    public List<ChampionshipParticipation> findByTeam(Team team){
        return cpr.findByTeam(team);
    }
    public Optional<ChampionshipParticipation> findById(UUID id){
        return cpr.findById(id);
    }
    public void deleteOne(UUID id){
        cpr.deleteById(id);
    }
    public ChampionshipParticipation update(ChampionshipParticipation cp){
        return cpr.save(cp);
    }



}
