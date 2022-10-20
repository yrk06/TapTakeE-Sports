package com.taptake.backend.service;

import com.taptake.backend.model.Championship;
import com.taptake.backend.model.Match;
import com.taptake.backend.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MatchService {
    @Autowired
    private MatchRepository matchRepository;

    @Transactional
    public Match save(Match match) {
        return matchRepository.save(match);
    }

    public void delete(UUID id) {
        matchRepository.deleteById(id);
    }

    public List<Match> findByChampionship(Championship championship) {
        return matchRepository.findByChampionship(championship);
    }

    public Match update(Match match) {
        return matchRepository.save(match);
    }

    public Optional<Match> findById(UUID id) {
        return matchRepository.findById(id);
    }
}
