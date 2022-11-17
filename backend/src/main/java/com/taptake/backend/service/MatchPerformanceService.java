package com.taptake.backend.service;

import com.taptake.backend.model.Match;
import com.taptake.backend.model.MatchPerformance;
import com.taptake.backend.model.Player;
import com.taptake.backend.repository.MatchPerformanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class MatchPerformanceService {
    @Autowired
    private MatchPerformanceRepository matchPerformanceRepository;

    @Transactional
    public MatchPerformance save(MatchPerformance matchPerformance) {
        return matchPerformanceRepository.save(matchPerformance);
    }

    public Optional<MatchPerformance> findById(int id) {
        return matchPerformanceRepository.findById(id);
    }

    public void delete(int id) {
        matchPerformanceRepository.deleteById(id);
    }

    public MatchPerformance update(MatchPerformance matchPerformance) {
        return matchPerformanceRepository.save(matchPerformance);
    }

    public List<MatchPerformance> findByPlayer(Player p) {
        return matchPerformanceRepository.findByPlayer(p);
    }

    public List<MatchPerformance> findByMatch(Match m) {
        return matchPerformanceRepository.findByMatch(m);
    }

    public List<MatchPerformance> findByPeriodAndPlayer(Date lowerBound, Date upperBound, Player p) {
        return matchPerformanceRepository.findByPeriodAndPlayer(lowerBound, upperBound, p);
    }

}
