package com.taptake.backend.service;

import com.taptake.backend.model.Championship;
import com.taptake.backend.repository.ChampionshipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ChampionshipService {

    @Autowired
    private ChampionshipRepository rep;

    public Championship save(Championship c) {
        return rep.save(c);
    }

    public Optional<Championship> findById(UUID id) {
        return rep.findById(id);
    }

    public Championship update(Championship c) {
        return rep.save(c);
    }

    public void deleteOne(UUID id) {
        rep.deleteById(id);
    }

    public List<Championship> findAllByNome(String nome) {
        return rep.findAllByNome(nome);
    }

    public List<Championship> findAll() {
        return rep.findAll();
    }
}
