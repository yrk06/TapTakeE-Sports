package com.taptake.backend.service;

import com.taptake.backend.model.MatchPerformance;
import com.taptake.backend.repository.MatchPerformanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class MatchPerformanceService {
    @Autowired
    private MatchPerformanceRepository matchPerformanceRepository;

    public MatchPerformance save(MatchPerformance matchPerformance){
        return matchPerformanceRepository.save(matchPerformance);
    }
    public Optional<MatchPerformance> findById(UUID id){
        return matchPerformanceRepository.findById(id);
    }
    public void delete(UUID id){
        matchPerformanceRepository.deleteById(id);
    }
    public MatchPerformance update(MatchPerformance matchPerformance){
        return matchPerformanceRepository.save(matchPerformance);
    }


}
