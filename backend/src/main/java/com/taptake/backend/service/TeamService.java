package com.taptake.backend.service;

import com.taptake.backend.model.Game;
import com.taptake.backend.model.Organization;
import com.taptake.backend.model.Team;
import com.taptake.backend.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TeamService {

    @Autowired
    private TeamRepository tRep;

    @Transactional
    public Team save(Team team){
        return tRep.save(team);
    }

    public void deleteOne(UUID id){
        tRep.deleteById(id);
    }

    public Team update(Team team){
        return tRep.save(team);
    }

    public Optional<Team> findById(UUID id){
        return tRep.findById(id);
    }
    public List<Team> findAllByOrg(Organization org){
        return tRep.findAllByOrg(org);
    }
    public List<Team> findAllByNomeTime(String nomeTime){
        return tRep.findAllByNomeTime(nomeTime);
    }

    public List<Team> findAll() {
        return tRep.findAll();
    }

    public List<Team> findAllByGame(Game game) {
        return tRep.findAllByGame(game);
    }
}
